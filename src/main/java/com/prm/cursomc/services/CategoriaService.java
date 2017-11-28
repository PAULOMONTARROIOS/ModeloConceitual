package com.prm.cursomc.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.prm.cursomc.domain.Categoria;
import com.prm.cursomc.repositories.CategoriaRepository;
import com.prm.cursomc.services.exceptions.ObjectNotFoundException;

@Service
public class CategoriaService {

	@Autowired
	private CategoriaRepository repo;
	
	public Categoria find(Integer id) {
		
		Categoria obj = repo.findOne(id);
		if(obj == null) {
			throw new ObjectNotFoundException("Recurso inexistente !! ");
		}
		return obj;
	}	
	
	public Categoria insert(Categoria obj) {
		obj.setId(null);
		Categoria cat = repo.save(obj);
		return cat;
	}
	
	public Categoria upadte(Categoria obj) {
		find(obj.getId());
		return repo.save(obj);	
	}
}