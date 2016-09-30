package br.com.hyperclass.caixaeletronico.restapi.serializer;

import java.io.IOException;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonGenerator;

import br.com.hyperclass.caixaeletronico.domain.contacorrente.eventos.EventoTransacional;
import br.com.hyperclass.caixaeletronico.domain.contacorrente.eventos.ValorTransferidoEvento;

@Component
public class TransferenciaSerializer extends DefaultEventoSerializer {

	@Override
	public void serialize(final EventoTransacional evento, final JsonGenerator generator) throws IOException {
		generator.writeStartObject();
		serializeDefaultValues(evento, generator);
		final ValorTransferidoEvento ev = (ValorTransferidoEvento) evento;
		generator.writeStringField("contaDestino", ev.getNumeroConta());
		generator.writeEndObject();
	}
}