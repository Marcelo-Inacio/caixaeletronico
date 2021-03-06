package br.com.hyperclass.caixaeletronico.restapi.serializer;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;

import br.com.hyperclass.caixaeletronico.restapi.wrapper.ValorWrapper;

public class ValorDeserializer extends JsonDeserializer<ValorWrapper> {

	@Override
	public ValorWrapper deserialize(final JsonParser jsonParser, final DeserializationContext context)
			throws IOException {
		
		final ObjectCodec oc = jsonParser.getCodec();
        final JsonNode node = oc.readTree(jsonParser);
        
        return new ValorWrapper(node.get("valor").asDouble());
        
	}

}
