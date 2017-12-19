package com.prm.cursomc;

import java.text.SimpleDateFormat;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.prm.cursomc.domain.Categoria;
import com.prm.cursomc.domain.Cidade;
import com.prm.cursomc.domain.Cliente;
import com.prm.cursomc.domain.Endereco;
import com.prm.cursomc.domain.Estado;
import com.prm.cursomc.domain.ItemPedido;
import com.prm.cursomc.domain.Pagamento;
import com.prm.cursomc.domain.PagamentoComBoleto;
import com.prm.cursomc.domain.PagamentoComCartao;
import com.prm.cursomc.domain.Pedido;
import com.prm.cursomc.domain.Produto;
import com.prm.cursomc.domain.enums.EstadoPagamento;
import com.prm.cursomc.domain.enums.TipoCliente;
import com.prm.cursomc.repositories.CategoriaRepository;
import com.prm.cursomc.repositories.CidadeRepository;
import com.prm.cursomc.repositories.ClienteRepository;
import com.prm.cursomc.repositories.EnderecoRepository;
import com.prm.cursomc.repositories.EstadoRepository;
import com.prm.cursomc.repositories.ItemPedidoRepository;
import com.prm.cursomc.repositories.PagamentoRepository;
import com.prm.cursomc.repositories.PedidoRepository;
import com.prm.cursomc.repositories.ProdutoRepository;

@SpringBootApplication
public class CursomcApplication implements CommandLineRunner{

	public static void main(String[] args) {
		SpringApplication.run(CursomcApplication.class, args);
	}

	@Override
	public void run(String... arg0) throws Exception {	
	}
}