package com.desafio.meli.simio.exception;

public class LetraNaoPermitidaException extends ExcecaoGenerica {

	private static final long serialVersionUID = 1L;

	public LetraNaoPermitidaException() {
		super("O DNA informado contém letras não aceitas");
		
	}
}
