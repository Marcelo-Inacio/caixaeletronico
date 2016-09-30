package br.com.hyperclass.caixaeletronico.restapi.serializer;

import java.io.IOException;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import br.com.hyperclass.caixaeletronico.domain.contacorrente.eventos.EventoTransacional;
import br.com.hyperclass.caixaeletronico.domain.contacorrente.eventos.TipoEvento;
import br.com.hyperclass.caixaeletronico.restapi.wrapper.ExtratoWrapper;

public class ExtratoSerializer extends JsonSerializer<ExtratoWrapper> {

	@Autowired
	@Qualifier("mapaSerializer")
	private Map<TipoEvento, Serializer> mapa;

	@Override
	public void serialize(ExtratoWrapper extrato, JsonGenerator generator, SerializerProvider provider)
			throws IOException, JsonProcessingException {
		
		generator.writeStartArray();
		for(final EventoTransacional evento : extrato.getExtrato()) {
			//generator.writeStartObject();
			final Serializer s = mapa.get(evento.getTipo());
			s.serialize(evento, generator);
			//generator.writeEndObject();
		}
		generator.writeEndArray();
		
	}

}
