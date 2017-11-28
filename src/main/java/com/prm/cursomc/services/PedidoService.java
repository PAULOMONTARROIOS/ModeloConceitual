package com.prm.cursomc.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.prm.cursomc.domain.Pedido;
import com.prm.cursomc.repositories.PedidoRepository;
import com.prm.cursomc.services.exceptions.ObjectNotFoundException;

@Service
public class PedidoService {

	@Autowired
	private PedidoRepository pedidoRepository;
	
	public Pedido find(Integer id) {
 		Pedido pedido = pedidoRepository.findOne(id);
 		if(pedido == null) {
 			throw new ObjectNotFoundException("Nada para retornar / " + Pedido.class.getName());
 		}
 		return pedido;
	}
}
