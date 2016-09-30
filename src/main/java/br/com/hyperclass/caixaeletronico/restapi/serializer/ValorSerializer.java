package br.com.hyperclass.caixaeletronico.restapi.serializer;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import br.com.hyperclass.caixaeletronico.restapi.wrapper.ValorWrapper;

public class ValorSerializer extends JsonSerializer<ValorWrapper> {

	@Override
	public void serialize(ValorWrapper evento, JsonGenerator generator, SerializerProvider arg2)
			throws IOException {

		generator.writeStartObject();
		generator.writeNumberField("valor", evento.getValor());
		generator.writeEndObject();
		
	}

}
