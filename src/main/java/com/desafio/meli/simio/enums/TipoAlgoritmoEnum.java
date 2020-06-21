package com.desafio.meli.simio.enums;

public enum TipoAlgoritmoEnum {
	
	SHA256("SHA-256");
	
	private String nome;
	
	TipoAlgoritmoEnum(String nome){
		this.nome = nome;
	}
	
	public String getNome() {
		return nome;
	}

}
