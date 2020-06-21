package com.desafio.meli.simio.exception;

public class DnaJaCadastradoException extends ExcecaoGenerica {

	private static final long serialVersionUID = 1L;

	public DnaJaCadastradoException() {
		super("O DNA informado já foi cadastrado");
		
	}
}
