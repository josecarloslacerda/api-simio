package com.desafio.meli.simio.util;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.desafio.meli.simio.exception.EncriptaDNAEmSHAException;
import com.desafio.meli.simio.exception.MatrizNaoQuadradaException;
import com.desafio.meli.simio.util.Encriptador;

public class EncriptadorTest {
	
	@Test
	@DisplayName("Valida encriptador em SHA-256")
	void validaencriptaValor() {
		String texto = "ATCG";
		String textoEncriptadoEmSHA256 = "8bbb9746434159bee76cc1510c01a83746785fe3ad54d46fbb9b5d6bd9497e61";
		assertEquals(textoEncriptadoEmSHA256, Encriptador.encriptaSHA256(texto));
	}
	
	@Test
	@DisplayName("Valida exceção do encriptador em SHA-256")
	void validaExcecaoencriptaValor() {
		assertThrows(EncriptaDNAEmSHAException.class, () -> Encriptador.encriptaSHA256(null));
	}

}
