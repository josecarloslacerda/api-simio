package com.desafio.meli.simio.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import com.desafio.meli.simio.enums.TipoDNAEnum;
import com.desafio.meli.simio.exception.DnaNuloException;
import com.desafio.meli.simio.exception.LetraNaoPermitidaException;
import com.desafio.meli.simio.exception.MatrizNaoQuadradaException;
import com.desafio.meli.simio.exception.TamanhoMinimoDaMatrizNaoAceitoException;

@Entity
@Table(name="TB_DNA")
public class DNA implements Serializable {

	private static final long serialVersionUID = 1L;
	private static final char[] SEQUENCIA = {'A', 'T', 'C', 'G'};
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long id;
	
	@NotNull
	@Column(unique = true)
	private String dna;
	
	@NotNull
	private int matriz;
	
	@Enumerated(EnumType.ORDINAL)
	@NotNull
	private TipoDNAEnum tipoDNA;
	
	public DNA() {
	}
	
	public DNA(String[] dna) {
		this.setDna(String.join("", dna).toUpperCase());
		this.setMatriz(dna.length);
	}
	
	public boolean contemApenasAsLetrasDaSequencia(){
		//Nessa verificação a busca linear se saiu mais
		// performática que o Regex 
		
		String valores = this.getDna();
		
		for (int i=0; i < valores.length(); i++ ){
			char valor = valores.charAt(i);
			
			boolean contem = false;
			for (int j = 0; j < SEQUENCIA.length; j++) {
				if (valor == SEQUENCIA[j]) {
					contem = true;
					break;
				}
			}
			
			if (!contem) {
				return false;
			}
			
		}
		
		return true;
		
	}
	
	public boolean ehMatrizMenorQue(int tamanhoMinimo){
		int n = this.getMatriz();
		int m = this.getDna().length() / n;
		
		if (n < tamanhoMinimo || m < tamanhoMinimo) {
			return true;
		}
		
		return false;
	}
	
	public boolean ehMatrizQuadrada() {
		int n = this.getMatriz();
		int m = this.getDna().length() / n;
		
		if (n != m) {
			return false;
		}
		
		return true;
	}
	

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getDna() {
		return dna;
	}

	public void setDna(String dna) {
		this.dna = dna;
	}

	public int getMatriz() {
		return matriz;
	}

	public void setMatriz(int matriz) {
		this.matriz = matriz;
	}

	public TipoDNAEnum getTipoDNA() {
		return tipoDNA;
	}

	public void setTipoDNA(TipoDNAEnum tipoDNA) {
		this.tipoDNA = tipoDNA;
	}
	
	
	public static class Factory{
		
		public Factory(){}
		
		public static DNA criar(boolean matrizQuadrada, int tamanhoMinimo, String[] dna) {
			
			ehDnaNulo(dna);
		
			DNA dnaRecebido = new DNA(dna);
			
			if (matrizQuadrada) {
				ehMatrizQuadrada(dnaRecebido);
			}
			ehMatrizMenorQue(dnaRecebido, tamanhoMinimo);
			contemApenasAsLetrasDaSequencia(dnaRecebido);
			
			return dnaRecebido;
		}
		
		private static void ehMatrizQuadrada(DNA dna) {
			
			if (!dna.ehMatrizQuadrada()) {
				throw new MatrizNaoQuadradaException();
			}
			
		}
		
		private static void ehDnaNulo(String[] dna) {
			if (dna == null) {
				throw new DnaNuloException();
			}
		}
		
		private static void ehMatrizMenorQue(DNA dna, int tamanhoMinimo){
			
			if (dna.ehMatrizMenorQue(tamanhoMinimo)) {
				throw new TamanhoMinimoDaMatrizNaoAceitoException(); 
			}
			
		}
		
		private static void contemApenasAsLetrasDaSequencia(DNA dna) {
			if (!dna.contemApenasAsLetrasDaSequencia()) {
				throw new LetraNaoPermitidaException();
			}
		}
	}
	

}
