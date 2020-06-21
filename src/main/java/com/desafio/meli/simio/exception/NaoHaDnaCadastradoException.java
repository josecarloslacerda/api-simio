package com.desafio.meli.simio.exception;

public class NaoHaDnaCadastradoException extends ExcecaoGenerica {

	private static final long serialVersionUID = 1L;

	public NaoHaDnaCadastradoException() {
		super("Não há DNA cadastrado");
	}
}
