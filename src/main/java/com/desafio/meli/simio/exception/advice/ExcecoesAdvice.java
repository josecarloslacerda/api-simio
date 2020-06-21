package com.desafio.meli.simio.exception.advice;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import com.desafio.meli.simio.exception.DnaJaCadastradoException;
import com.desafio.meli.simio.exception.DnaNaoEhSimioException;
import com.desafio.meli.simio.exception.DnaNuloException;
import com.desafio.meli.simio.exception.EncriptaDNAEmSHAException;
import com.desafio.meli.simio.exception.ExcecaoGenerica;
import com.desafio.meli.simio.exception.LetraNaoPermitidaException;
import com.desafio.meli.simio.exception.MatrizNaoQuadradaException;
import com.desafio.meli.simio.exception.NaoHaDnaCadastradoException;
import com.desafio.meli.simio.exception.TamanhoMinimoDaMatrizNaoAceitoException;
import com.desafio.meli.simio.response.Response;

@ControllerAdvice
public class ExcecoesAdvice {

	@ResponseBody
	@ExceptionHandler(Exception.class)
	public ResponseEntity<?> excecaoNaoTratada(Exception ex){
		Response<?> response = Response.get();
		response.setErro("Erro não tratado pela API. Uma exceção genérica ocorreu: " + ex.getMessage());
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
	}

	
	@ResponseBody
	@ExceptionHandler(value = {DnaJaCadastradoException.class, 
			DnaNaoEhSimioException.class,
			DnaNuloException.class, 
			EncriptaDNAEmSHAException.class,
			LetraNaoPermitidaException.class, 
			MatrizNaoQuadradaException.class,
			NaoHaDnaCadastradoException.class, 
			TamanhoMinimoDaMatrizNaoAceitoException.class})
	public ResponseEntity<?> usuarioNaoEncontradoHandler(ExcecaoGenerica ex){
		Response<?> response = Response.get();
		response.setErro(ex.getMensagem());
		return ResponseEntity.status(HttpStatus.FORBIDDEN).body(response);
	}
	
}
