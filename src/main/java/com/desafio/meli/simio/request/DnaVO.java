package com.desafio.meli.simio.request;

import java.io.Serializable;

public class DnaVO implements Serializable{
	private static final long serialVersionUID = 1L;

	String dna[];

	public String[] getDna() {
		return dna;
	}

	public void setData(String[] dna) {
		this.dna = dna;
	}
	
}
