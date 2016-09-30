package br.com.hyperclass.caixaeletronico.restapi.wrapper;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import br.com.hyperclass.caixaeletronico.restapi.serializer.ValorDeserializer;
import br.com.hyperclass.caixaeletronico.restapi.serializer.ValorSerializer;

@JsonDeserialize(using = ValorDeserializer.class)
@JsonSerialize(using = ValorSerializer.class)
public class ValorWrapper {
	
	private final double valor;
	
	public ValorWrapper(final double valor) {
		this.valor = valor;
	}
	
	public double getValor() {
		return valor;
	}

}
