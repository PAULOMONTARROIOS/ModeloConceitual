package com.prm.cursomc;

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
import com.prm.cursomc.domain.Produto;
import com.prm.cursomc.domain.enums.TipoCliente;
import com.prm.cursomc.repositories.CategoriaRepository;
import com.prm.cursomc.repositories.CidadeRepository;
import com.prm.cursomc.repositories.ClienteRepository;
import com.prm.cursomc.repositories.EnderecoRepository;
import com.prm.cursomc.repositories.EstadoRepository;
import com.prm.cursomc.repositories.ProdutoRepository;

@SpringBootApplication
public class CursomcApplication implements CommandLineRunner{

	@Autowired
	private CategoriaRepository categoriaRepository;
	
	@Autowired
	private ProdutoRepository produtoRepository;
	
	@Autowired
	private EstadoRepository estadoRepository;
	
	@Autowired
	private CidadeRepository cidadeRepository;
	
	@Autowired
	private ClienteRepository clienteRepository;
	
	@Autowired
	private EnderecoRepository enderecoRepository;
	
	
	public static void main(String[] args) {
		SpringApplication.run(CursomcApplication.class, args);
	}

	@Override
	public void run(String... arg0) throws Exception {
		Categoria c1 = new Categoria(null,"Informática");
		Categoria c2 = new Categoria(null,"Escritório");
		
		Produto p1 = new Produto(null,"Computador",2000.00);
		Produto p2 = new Produto(null,"Impressora",800.00);
		Produto p3 = new Produto(null,"Mouse",80.00);
		
		c1.getProdutos().addAll(Arrays.asList(p1,p2,p3));
		c2.getProdutos().addAll(Arrays.asList(p2));
		
		p1.getCategorias().addAll(Arrays.asList(c1));
		p2.getCategorias().addAll(Arrays.asList(c1,c2));
		p3.getCategorias().addAll(Arrays.asList(c1));
		
		categoriaRepository.save(Arrays.asList(c1,c2));
		produtoRepository.save(Arrays.asList(p1,p2,p3));

		//  Separação
		
		Estado est1 = new Estado(null,"Minas Gerais");
		Estado est2 = new Estado(null, "São Paulo");
		
		Cidade cd1 = new Cidade(null,"Uberlândia",est1);
		Cidade cd2 = new Cidade(null,"São Paulo",est2);
		Cidade cd3 = new Cidade(null,"Campinas",est2);
		
		est1.getCidades().addAll(Arrays.asList(cd1));
		est2.getCidades().addAll(Arrays.asList(cd2,cd3));
		
		estadoRepository.save(Arrays.asList(est1,est2));
		cidadeRepository.save(Arrays.asList(cd1,cd2,cd3));
		
		
		Cliente cli1 = new Cliente(null, "Maria Silva","maria@gmail.com","09908805513",TipoCliente.PESSOAFISICA);
		cli1.getTelefones().addAll(Arrays.asList("81999668553","8130536565"));
		
		Endereco e1 = new Endereco(null, "Rua Flores", "300", "Apto 203", "Jardim", "38220834", cli1, cd1);
		Endereco e2 = new Endereco(null, "Avenidade Matos", "105", "Sala 800", "Centro", "387777012", cli1, cd2);
		
		cli1.getEnderecos().addAll(Arrays.asList(e1,e2));
		
		clienteRepository.save(Arrays.asList(cli1));
		enderecoRepository.save(Arrays.asList(e1,e2));
		
		
	}
}
