package com.prm.cursomc.services;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.prm.cursomc.domain.ItemPedido;
import com.prm.cursomc.domain.PagamentoComBoleto;
import com.prm.cursomc.domain.Pedido;
import com.prm.cursomc.domain.enums.EstadoPagamento;
import com.prm.cursomc.repositories.ItemPedidoRepository;
import com.prm.cursomc.repositories.PagamentoRepository;
import com.prm.cursomc.repositories.PedidoRepository;
import com.prm.cursomc.repositories.ProdutoRepository;
import com.prm.cursomc.services.exceptions.ObjectNotFoundException;

@Service
public class PedidoService {

	@Autowired
	private PedidoRepository pedidoRepository;
	
	@Autowired
	private BoletoService boletoService;
	
	@Autowired
	private PagamentoRepository pagamentoRepository;
	
	@Autowired
	private ProdutoRepository produtoRepository;
	
	@Autowired
	private ItemPedidoRepository itemPedidoRepository;

	public Pedido find(Integer id) {
		Pedido pedido = pedidoRepository.findOne(id);
		if (pedido == null) {
			throw new ObjectNotFoundException("Nada para retornar / " + Pedido.class.getName());
		}
		return pedido;
	}
	
	public Pedido insert(Pedido obj) {
		obj.setId(null);
		obj.setInstante(new Date());
		obj.getPagamento().setEstadoPagamento(EstadoPagamento.PENDENTE);
		obj.getPagamento().setPedido(obj);
		if(obj.getPagamento() instanceof PagamentoComBoleto) {
			PagamentoComBoleto pagto = (PagamentoComBoleto) obj.getPagamento();
			boletoService.preencherPagamentoComBoleto(pagto,obj.getInstante());
		}
		
		obj = pedidoRepository.save(obj);
		pagamentoRepository.save(obj.getPagamento());
		for(ItemPedido ip :  obj.getItens()) {
			ip.setDesconto(0.0);
			ip.setPreco(produtoRepository.findOne(ip.getProduto().getId()).getPreco());
			ip.setPedido(obj);
		}
		itemPedidoRepository.save(obj.getItens());
		return obj;
	}
}
