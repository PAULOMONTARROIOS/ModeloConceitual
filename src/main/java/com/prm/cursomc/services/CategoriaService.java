package com.prm.cursomc.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.prm.cursomc.domain.Categoria;
import com.prm.cursomc.repositories.CategoriaRepository;
import com.prm.cursomc.services.exceptions.DataIntegrityException;
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
	
	public void delete(Integer id) {
		find(id);
		try {
			repo.delete(id);
		}catch(DataIntegrityViolationException e){
			throw new DataIntegrityException("Não é possível excluir este recurso.");
		}
	}
	
	public List<Categoria> findAll(){
		List<Categoria> lista = repo.findAll();
		return lista;		
	}
	
	public Page<Categoria> findPage(Integer page, Integer linesPerPage, String orderBy, String direction){
		PageRequest pageRequest =  new PageRequest(page, linesPerPage, Direction.valueOf(direction), orderBy);
		return repo.findAll(pageRequest);
	}
}