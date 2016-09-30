package br.com.hyperclass.caixaeletronico.restapi.wrapper;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import br.com.hyperclass.caixaeletronico.restapi.serializer.TransferenciaDeserializer;

@JsonDeserialize(using = TransferenciaDeserializer.class)
//@JsonSerialize(using = TransferenciaSerializer.class)
public class TransferenciaWrapper {
	
	private final String contaDestino;
	private final double valor;
	
	public TransferenciaWrapper(final String contaDestino, final double valor) {
		this.contaDestino = contaDestino;
		this.valor = valor;
	}
	
	public String getContaDestino() {
		return contaDestino;
	}
	
	public double getValor() {
		return valor;
	}

}
