package com.prm.cursomc.services;

import java.net.NetworkInterface;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.prm.cursomc.domain.Cliente;
import com.prm.cursomc.dto.ClienteDTO;
import com.prm.cursomc.repositories.ClienteRepository;
import com.prm.cursomc.services.exceptions.DataIntegrityException;
import com.prm.cursomc.services.exceptions.ObjectNotFoundException;

@Service
public class ClienteService {

	@Autowired
	private ClienteRepository clienteRepository;
	
	public Cliente find(Integer id) {
		Cliente obj = clienteRepository.findOne(id);
		
		if(obj == null) {
			throw new ObjectNotFoundException("Objeto inválido !");
		}
		return obj;	
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
	
	private void updateData(Cliente newObj, Cliente obj) {
		newObj.setNome(obj.getNome());
		newObj.setEmail(obj.getEmail());
	}
	
}