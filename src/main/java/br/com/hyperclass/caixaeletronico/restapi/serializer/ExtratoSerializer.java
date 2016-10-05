package br.com.hyperclass.caixaeletronico.restapi.serializer;

import java.io.IOException;
import java.util.EnumMap;
import java.util.Map;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import br.com.hyperclass.caixaeletronico.domain.contacorrente.eventos.EventoTransacional;
import br.com.hyperclass.caixaeletronico.domain.contacorrente.eventos.TipoEvento;
import br.com.hyperclass.caixaeletronico.restapi.wrapper.ExtratoWrapper;
import br.com.hyperclass.caixaeletronico.util.BeanRetriever;

public class ExtratoSerializer extends JsonSerializer<ExtratoWrapper> {

	private final Map<TipoEvento, Serializer> map = new EnumMap<>(TipoEvento.class);
	
	@SuppressWarnings("unchecked")
	public ExtratoSerializer() {
		super();
		map.putAll(BeanRetriever.getBean("mapaSerializer", Map.class));
	}
	
	@Override
	public void serialize(final ExtratoWrapper extrato, final JsonGenerator generator, final SerializerProvider provider)
			throws IOException {

		generator.writeStartArray();
		for(final EventoTransacional evento : extrato.getExtrato()) {
			final Serializer serializer = map.get(evento.getTipo());
			serializer.serialize(evento, generator);
		}
		generator.writeEndArray();
	}
}
