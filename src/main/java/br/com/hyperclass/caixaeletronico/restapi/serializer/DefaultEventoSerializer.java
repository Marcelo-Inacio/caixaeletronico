package br.com.hyperclass.caixaeletronico.restapi.serializer;

import java.io.IOException;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonGenerator;

import br.com.hyperclass.caixaeletronico.domain.contacorrente.eventos.EventoTransacional;

@Component
public class DefaultEventoSerializer implements Serializer {
	
	protected void serializeDefaultValues(final EventoTransacional evento, final JsonGenerator generator) throws IOException {
		generator.writeStringField("operação", evento.getTipo().name());
		generator.writeNumberField("data", evento.getData().getTime());
		generator.writeNumberField("valor", evento.getValor());
	}

	@Override
	public void serialize(final EventoTransacional evento, final JsonGenerator generator) throws IOException {
			generator.writeStartObject();
			serializeDefaultValues(evento, generator);
			generator.writeEndObject();
	}
	
}
