package com.prm.cursomc.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.prm.cursomc.domain.Cidade;
import com.prm.cursomc.domain.Cliente;
import com.prm.cursomc.domain.Endereco;
import com.prm.cursomc.domain.enums.TipoCliente;
import com.prm.cursomc.dto.ClienteDTO;
import com.prm.cursomc.dto.ClienteNewDTO;
import com.prm.cursomc.repositories.CidadeRepository;
import com.prm.cursomc.repositories.ClienteRepository;
import com.prm.cursomc.repositories.EnderecoRepository;
import com.prm.cursomc.services.exceptions.DataIntegrityException;
import com.prm.cursomc.services.exceptions.ObjectNotFoundException;

@Service
public class ClienteService {

	@Autowired
	private ClienteRepository clienteRepository;
	
	@Autowired
	private CidadeRepository cidadeRepository;
	
	@Autowired
	private EnderecoRepository enderecoRepository;
	
	public Cliente find(Integer id) {
		Cliente obj = clienteRepository.findOne(id);
		
		if(obj == null) {
			throw new ObjectNotFoundException("Objeto inválido !");
		}
		return obj;	
	}
	
	public Cliente insert(Cliente obj) {
		obj.setId(null);
		Cliente cat = clienteRepository.save(obj);
		enderecoRepository.save(cat.getEnderecos());
		return cat;
	}
	
	public Cliente upadte(Cliente obj) {
		Cliente newObj = find(obj.getId());
		
		updateData(newObj,obj);
		return clienteRepository.save(newObj);	
	}
	
	public void delete(Integer id) {
		find(id);
		try {
			clienteRepository.delete(id);
		}catch(DataIntegrityViolationException e){
			throw new DataIntegrityException("Não é possível excluir este recurso por entidades relacionadas.");
		}
	}
	
	public List<Cliente> findAll(){
		List<Cliente> lista = clienteRepository.findAll();
		return lista;		
	}
	
	public Page<Cliente> findPage(Integer page, Integer linesPerPage, String orderBy, String direction){
		PageRequest pageRequest =  new PageRequest(page, linesPerPage, Direction.valueOf(direction), orderBy);
		return clienteRepository.findAll(pageRequest);
	}
	
	public Cliente fromDTO(ClienteDTO objDTO) {
		return new Cliente(objDTO.getId(),objDTO.getNome(),objDTO.getEmail(),null,null);
	}
	
	public Cliente fromDTO(ClienteNewDTO objDTO) {
		Cliente cli = new Cliente(null, objDTO.getNome(), objDTO.getEmail(), objDTO.getCpfOuCnpj(), TipoCliente.toEnum(objDTO.getTipo()));
		Cidade cid = cidadeRepository.findOne(objDTO.getCidadeId());
		Endereco end =  new Endereco(null, objDTO.getLogradouro(), objDTO.getNumero(), objDTO.getComplemento(), objDTO.getBairro(), objDTO.getCep(), cli, cid);
		cli.getEnderecos().add(end);
		cli.getTelefones().add(objDTO.getTelefone1());
		if(objDTO.getTelefone2() != null) {
			cli.getTelefones().add(objDTO.getTelefone2());
		}
		if(objDTO.getTelefone3() != null) {
			cli.getTelefones().add(objDTO.getTelefone3());
		}
		return cli;
		
	}
	
	private void updateData(Cliente newObj, Cliente obj) {
		newObj.setNome(obj.getNome());
		newObj.setEmail(obj.getEmail());
	}
	
}