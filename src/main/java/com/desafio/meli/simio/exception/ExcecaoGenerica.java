package com.desafio.meli.simio.exception;

public abstract class ExcecaoGenerica extends RuntimeException {

	private static final long serialVersionUID = 1L;
	
	private String mensagem;

	public ExcecaoGenerica(String mensagem){
		this.mensagem = mensagem;
	}

	public String getMensagem() {
		return mensagem;
	}
	
}
