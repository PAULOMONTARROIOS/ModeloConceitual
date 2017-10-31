package com.prm.cursomc.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.prm.cursomc.domain.Cliente;
import com.prm.cursomc.repositories.ClienteRepository;
import com.prm.cursomc.services.exceptions.ObjectNotFoundException;

@Service
public class ClienteService {

	@Autowired
	private ClienteRepository clienteRepository;
	
	public Cliente Buscar(Integer id) {
		Cliente obj = clienteRepository.findOne(id);
		
		if(obj == null) {
			throw new ObjectNotFoundException("Objeto inválido !");
		}
		return obj;	
	}	
}