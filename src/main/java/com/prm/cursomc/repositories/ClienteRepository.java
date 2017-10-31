package com.prm.cursomc.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.prm.cursomc.domain.Cliente;

public interface ClienteRepository extends JpaRepository<Cliente, Integer> {

}
