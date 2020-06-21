package com.desafio.meli.simio.response;

import java.io.Serializable;

public class Response<T> implements Serializable {

	private static final long serialVersionUID = 1L;

	private T data;
	
	private String erro;
	
	public static <T> Response<T> get(T objeto){
		Response<T> response = new Response<>();
		response.setData(objeto);
		return response;
	}

	public static <T> Response<T> get(){
		Response<T> response = new Response<>();
		return response;
	}
	
	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}

	public String getErro() {
		return erro;
	}

	public void setErro(String erro) {
		this.erro = erro;
	}
	
}
