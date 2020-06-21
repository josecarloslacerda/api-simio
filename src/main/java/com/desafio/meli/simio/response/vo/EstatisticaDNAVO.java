package com.desafio.meli.simio.response.vo;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;

public class EstatisticaDNAVO implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@JsonProperty("count_mutant_dna")
	private long quantidadeTotalDeSimio;
	
	@JsonProperty("count_human_dna")
	private long quantidadeTotalDeHumano;
	
	@JsonProperty("ratio")
	private float proporcaoSimioParaHumano;

	public long getQuantidadeTotalDeSimio() {
		return quantidadeTotalDeSimio;
	}

	public void setQuantidadeTotalDeSimio(long quantidadeTotalDeSimio) {
		this.quantidadeTotalDeSimio = quantidadeTotalDeSimio;
	}

	public long getQuantidadeTotalDeHumano() {
		return quantidadeTotalDeHumano;
	}

	public void setQuantidadeTotalDeHumano(long quantidadeTotalDeHumano) {
		this.quantidadeTotalDeHumano = quantidadeTotalDeHumano;
	}

	public float getProporcaoSimioParaHumano() {
		return proporcaoSimioParaHumano;
	}

	public void setProporcaoSimioParaHumano(float proporcaoSimioParaHumano) {
		this.proporcaoSimioParaHumano = proporcaoSimioParaHumano;
	}

}
