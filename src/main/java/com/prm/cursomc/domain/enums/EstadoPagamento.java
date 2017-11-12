package com.prm.cursomc.domain.enums;

import com.prm.cursomc.domain.Estado;

public enum EstadoPagamento {

	PENDENTE(1,"Pendente"),
	QUITADO(2,"Quitado"),
	CANCELADO(3,"Cancelado");
	
	private Integer id;
	private String descricao;
	
	EstadoPagamento(int id, String descricao) {
		this.id = id;
		this.descricao = descricao;
	}
	
	public Integer getId() {
		return this.id;
	}
	
	public String getDescricao() {
		return this.descricao;
	}
	
	public static EstadoPagamento toEnum(Integer id) {
		if(id == null) {
			return null;
		}
		
		for (EstadoPagamento x : EstadoPagamento.values()) {
			if(id.equals(x.getId())) {
				return x;
			}
		}
		
		throw new IllegalArgumentException();
	}
	
}
