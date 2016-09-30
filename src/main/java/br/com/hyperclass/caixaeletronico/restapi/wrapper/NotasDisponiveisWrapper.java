package br.com.hyperclass.caixaeletronico.restapi.wrapper;

import java.util.EnumMap;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import br.com.hyperclass.caixaeletronico.domain.caixa.Nota;
import br.com.hyperclass.caixaeletronico.domain.caixa.ValorNota;
import br.com.hyperclass.caixaeletronico.restapi.serializer.NotasDisponiveisSerializer;


@JsonSerialize(using = NotasDisponiveisSerializer.class)
public class NotasDisponiveisWrapper {
	
private final Map<ValorNota, List<Nota>> notas = new EnumMap<>(ValorNota.class);
	
	public NotasDisponiveisWrapper(final Map<ValorNota, List<Nota>> notas) {
		this.notas.putAll(notas);
	}
	
	public Map<ValorNota, List<Nota>> getNotas() {
		return new EnumMap<>(notas);	
	}

}
