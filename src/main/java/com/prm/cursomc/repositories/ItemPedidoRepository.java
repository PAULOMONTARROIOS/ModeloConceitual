package com.prm.cursomc.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.prm.cursomc.domain.ItemPedido;
import com.prm.cursomc.domain.ItemPedidoPK;

public interface ItemPedidoRepository extends JpaRepository<ItemPedido, Integer> {

}
