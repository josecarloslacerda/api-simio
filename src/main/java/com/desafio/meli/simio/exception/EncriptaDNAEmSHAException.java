package com.desafio.meli.simio.exception;

public class EncriptaDNAEmSHAException extends ExcecaoGenerica {

	private static final long serialVersionUID = 1L;

	public EncriptaDNAEmSHAException(Exception ex) {
		super("Ocorreu um erro ao tentar encriptar o DNA em SHA. Detalhes do erro: " + ex.getMessage());
	}
}
