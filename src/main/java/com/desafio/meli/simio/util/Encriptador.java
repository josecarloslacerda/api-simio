package com.desafio.meli.simio.util;

import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;

import com.desafio.meli.simio.enums.TipoAlgoritmoEnum;
import com.desafio.meli.simio.exception.EncriptaDNAEmSHAException;

public class Encriptador {
	
	public static String encriptaSHA256(String dna) {
		try {
			
			MessageDigest algoritmo = MessageDigest.getInstance(TipoAlgoritmoEnum.SHA256.getNome());
			algoritmo.update(dna.getBytes(StandardCharsets.UTF_8));
			return String.format("%064x", new BigInteger(1, algoritmo.digest()));
			
		} catch (Exception e) {
			throw new EncriptaDNAEmSHAException(e);
		}
		
	}

}
