package com.desafio.meli.simio.exception;

public class MatrizNaoQuadradaException extends ExcecaoGenerica {

	private static final long serialVersionUID = 1L;

	public MatrizNaoQuadradaException() {
		super("A matriz do DNA deve ser quadrada.");
		
	}
}
