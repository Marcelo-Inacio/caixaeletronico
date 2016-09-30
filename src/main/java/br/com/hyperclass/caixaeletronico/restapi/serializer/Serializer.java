package br.com.hyperclass.caixaeletronico.restapi.serializer;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonGenerator;

import br.com.hyperclass.caixaeletronico.domain.contacorrente.eventos.EventoTransacional;

public interface Serializer {
	
	public void serialize(final EventoTransacional evento, final JsonGenerator generator) throws IOException;

}
