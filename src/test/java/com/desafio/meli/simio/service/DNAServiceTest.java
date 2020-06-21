package com.desafio.meli.simio.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.desafio.meli.simio.exception.DnaJaCadastradoException;
import com.desafio.meli.simio.exception.DnaNuloException;
import com.desafio.meli.simio.exception.LetraNaoPermitidaException;
import com.desafio.meli.simio.exception.MatrizNaoQuadradaException;
import com.desafio.meli.simio.exception.NaoHaDnaCadastradoException;
import com.desafio.meli.simio.exception.TamanhoMinimoDaMatrizNaoAceitoException;
import com.desafio.meli.simio.response.vo.EstatisticaDNAVO;

@ActiveProfiles("test")
@SpringBootTest
@ExtendWith(SpringExtension.class)
@TestMethodOrder(OrderAnnotation.class)
public class DNAServiceTest {
	
	@Autowired
	DNAService dnaService;
	
	@Order(1)
	@Test
	@DisplayName("Verificar se contém DNA cadastrado")
	void naoHaDnaCadastrado() {
		assertThrows(NaoHaDnaCadastradoException.class, () -> dnaService.stats());
	}
	
	@Order(2)
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
	
	@Order(3)
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
	
	@Order(4)
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

	@Order(5)
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
	
	@Order(6)
	@Test
	@DisplayName("Verificar proporção quando há apenas Símio")
	void verificarProporcaoQuandoHaApenasSimio() {
		EstatisticaDNAVO estatistica = dnaService.stats();
		
		assertEquals(4, estatistica.getQuantidadeTotalDeSimio());
		assertEquals(0, estatistica.getQuantidadeTotalDeHumano());
		assertEquals(0, estatistica.getProporcaoSimioParaHumano());
		
	}
	
	@Order(7)
	@Test
	@DisplayName("Verifica se a sequência é de um Humano")
	void verificaSeSequenciaEhHumano() {
		String dna[] = {
				"ATGCAT", 
				"GCATGC", 
				"ATGCAT", 
				"GCATGC", 
				"ATGCAT", 
				"GCATGC"};
		
		assertFalse(dnaService.isSimian(dna));
	}
	
	@Order(8)
	@Test
	@DisplayName("Verificar proporção de Símio em relação a Humano")
	void verificarProporcaoSimioHumanoCadastrados() {
		EstatisticaDNAVO estatistica = dnaService.stats();
		
		assertEquals(4, estatistica.getQuantidadeTotalDeSimio());
		assertEquals(1, estatistica.getQuantidadeTotalDeHumano());
		assertEquals(4, estatistica.getProporcaoSimioParaHumano());
		
	}
	
	@Order(9)
	@Test
	@DisplayName("Verifica DNA já cadastrado")
	void verificaDNAJaCadastrado() {
		
		String dna[] = {
				"ATACAT", 
				"GCATGC", 
				"ATGCAT", 
				"GCATGC", 
				"ATGCAT", 
				"GCATGC"};
		
		assertFalse(dnaService.isSimian(dna));
		
		assertThrows(DnaJaCadastradoException.class, () -> dnaService.isSimian(dna));
	}
	
	@Order(10)
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
	
	@Order(11)
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
	
	@Order(12)
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
	
	@Order(13)
	@Test
	@DisplayName("Verificar se DNA é nulo")
	void dnaNulo() {
		
		String dna[] = null;
		
		assertThrows(DnaNuloException.class, () -> dnaService.isSimian(dna));
		
	}
	
}
