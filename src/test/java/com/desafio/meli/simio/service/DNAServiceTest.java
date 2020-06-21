package com.desafio.meli.simio.service;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.desafio.meli.simio.exception.DnaNuloException;
import com.desafio.meli.simio.exception.LetraNaoPermitidaException;
import com.desafio.meli.simio.exception.MatrizNaoQuadradaException;
import com.desafio.meli.simio.exception.TamanhoMinimoDaMatrizNaoAceitoException;
import com.desafio.meli.simio.repository.DNARepository;
import com.desafio.meli.simio.service.impl.DNAServiceImpl;

public class DNAServiceTest {
	
	@Autowired
	DNARepository dnaRepository;
	
	DNAService dnaService;
	
	@BeforeAll
	void init() {
		dnaService = new DNAServiceImpl(dnaRepository);
	}
	
	@Test
	@DisplayName("Verifica se contem sequência de Simio na horizontal")
	void contemSequenciaDeSimioNaHorizontal() {
		
		String dna[] = {
				"ATGCAT", 
				"GCAAAA", 
				"ATGCAT", 
				"GCATGC", 
				"ATGCAT", 
				"GCATGC"};
		
		assertTrue(dnaService.isSimian(dna));
	}
	
	@Test
	@DisplayName("Verifica que Não contem sequência de Simio na horizontal")
	void naoContemSequenciaDeSimioNaHorizontal() {
		String dna[] = {
				"ATGCAT", 
				"GCATGC", 
				"ATGCAT", 
				"GCATGC", 
				"ATGCAT", 
				"GCATGC"};
		
		assertFalse(dnaService.isSimian(dna));
	}
	
	@Test
	@DisplayName("Verifica se contem sequência de Simio na vertical")
	void contemSequenciaSimioNaVertical() {
		String dna[] = {
				"ATGCAT", 
				"GCATGC", 
				"ATGTAT", 
				"GCATGC", 
				"ATGTAT", 
				"GCATGC"};
		
		assertTrue(dnaService.isSimian(dna));
	}
	
	@Test
	@DisplayName("Verifica que Não contem sequência de Simio na vertical")
	void naoContemSequenciaDeSimioNaVertical() {
		String dna[] = {
				"ATGCAT", 
				"GCATGC", 
				"ATGCAT", 
				"GCATGC", 
				"ATGCAT", 
				"GCATGC"};
		
		assertFalse(dnaService.isSimian(dna));
	}
	
	@Test
	@DisplayName("Verifica se contem sequência de Simio na diagonal crescente")
	void contemSequenciaSimioNaDiagonalCrescente() {
		String dna[] = {
				"ATGCAT", 
				"GCATTC", 
				"ATGTAT", 
				"GCTTGC", 
				"ATGCAT", 
				"GCATGC"};
		
		assertTrue(dnaService.isSimian(dna));
	}
	
	@Test
	@DisplayName("Verifica que Não contem sequência de Simio na diagonal crescente")
	void naoContemSequenciaDeSimioNaDiagonaCrescente() {
		String dna[] = {
				"ATGCAT", 
				"GCATGC", 
				"ATGCAT", 
				"GCATGC", 
				"ATGCAT", 
				"GCATGC"};
		
		assertFalse(dnaService.isSimian(dna));
	}

	@Test
	@DisplayName("Verifica se contem sequência de Simio na diagonal decrescente")
	void contemSequenciaSimioNaDiagonalDecrescente() {
		String dna[] = {
				"ATGCAT", 
				"GCATGC", 
				"ATGCAT", 
				"GAATGC", 
				"ATACAT", 
				"GCAAGC"};
		
		assertTrue(dnaService.isSimian(dna));
	}
	
	@Test
	@DisplayName("Verifica que Não contem sequência de Simio na diagonal decrescente")
	void naoContemSequenciaDeSimioNaDiagonaDecrescente() {
		String dna[] = {
				"ATGCAT", 
				"GCATGC", 
				"ATGCAT", 
				"GCATGC", 
				"ATGCAT", 
				"GCATGC"};
		
		assertFalse(dnaService.isSimian(dna));
	}
	
	@Test
	@DisplayName("Não contém apenas as letras A, T, C e G")
	void naoContemApenasAsLetrasATCG() {
		String dna[] = {
				"ATGCAT", 
				"GCATGC", 
				"ATGCAT", 
				"GCAZGC", 
				"ATGCAT", 
				"GCATGC"};
		
		assertThrows(LetraNaoPermitidaException.class, () -> dnaService.isSimian(dna));
		
	}
	
	@Test
	@DisplayName("Tamanho mínimo da matrz não aceito")
	void tamanhoMininoDaMatrizNaoAceito() {
		String dna2x2[] = {
				"AT", 
				"GC"};
		
		assertThrows(TamanhoMinimoDaMatrizNaoAceitoException.class, () -> dnaService.isSimian(dna2x2));
		
		String dna3X3[] = {
				"ATG", 
				"GCA", 
				"ATG"};
		assertThrows(TamanhoMinimoDaMatrizNaoAceitoException.class, () -> dnaService.isSimian(dna3X3));
		
	}
	
	@Test
	@DisplayName("Matriz não quadrada")
	void matrizNaoQuadra() {
		
		String dna[] = {
				"ATGCAT", 
				"GCATGC", 
				"ATGCAT", 
				"GCAZGC", 
				"ATGCAT"};
		
		assertThrows(MatrizNaoQuadradaException.class, () -> dnaService.isSimian(dna));
		
	}
	
	@Test
	@DisplayName("Verificar se DNA é nulo")
	void dnaNulo() {
		
		String dna[] = null;
		
		assertThrows(DnaNuloException.class, () -> dnaService.isSimian(dna));
		
	}
	
}
