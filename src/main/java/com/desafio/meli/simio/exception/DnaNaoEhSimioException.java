package com.desafio.meli.simio.exception;

public class DnaNaoEhSimioException extends ExcecaoGenerica {

	private static final long serialVersionUID = 1L;

	public DnaNaoEhSimioException() {
		super("A matriz do DNA não é de um Símio.");
	}
}
