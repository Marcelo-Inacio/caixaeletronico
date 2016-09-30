package br.com.hyperclass.caixaeletronico.restapi.serializer;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import br.com.hyperclass.caixaeletronico.domain.caixa.Nota;
import br.com.hyperclass.caixaeletronico.domain.caixa.ValorNota;
import br.com.hyperclass.caixaeletronico.restapi.wrapper.NotasDisponiveisWrapper;


public class NotasDisponiveisSerializer extends JsonSerializer<NotasDisponiveisWrapper> {

	@Override
	public void serialize(NotasDisponiveisWrapper notasDisponiveisWrapper, JsonGenerator generator, SerializerProvider arg2)
			throws IOException {
		
		generator.writeStartArray();
		for(ValorNota nota : notasDisponiveis(notasDisponiveisWrapper.getNotas())) {
			generator.writeStartObject();
			generator.writeNumberField(nota.name(), nota.valor());
			generator.writeEndObject();
		}
		generator.writeEndArray();
		
	}
	
	private List<ValorNota> notasDisponiveis(final Map<ValorNota, List<Nota>> notasMapa) {
		List<ValorNota> listaNotas = new ArrayList<>();
		for (Entry<ValorNota, List<Nota>> entry : notasMapa.entrySet()) {
	    	ValorNota nota = entry.getKey();
	    	List<Nota> lista = entry.getValue();
	    	if(!lista.isEmpty()){
	    		listaNotas.add(nota);
	    	}
	    }
		return listaNotas;
	}

}
