package com.desafio.meli.simio.exception;

public class TamanhoMinimoDaMatrizNaoAceitoException extends ExcecaoGenerica {

	private static final long serialVersionUID = 1L;

	public TamanhoMinimoDaMatrizNaoAceitoException() {
		super("Tamanho da matriz do DNA não aceito. O tamanho mínimo aceitável é 4");
		
	}
}
