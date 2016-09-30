package br.com.hyperclass.caixaeletronico.restapi.wrapper;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import br.com.hyperclass.caixaeletronico.domain.contacorrente.eventos.EventoTransacional;
import br.com.hyperclass.caixaeletronico.restapi.serializer.ExtratoSerializer;

@JsonSerialize(using = ExtratoSerializer.class)
public class ExtratoWrapper {
	
	private final List<EventoTransacional> extrato = new ArrayList<>();
	
	public ExtratoWrapper(final List<EventoTransacional> extrato) {
		this.extrato.addAll(extrato);
	}
	
	public List<EventoTransacional> getExtrato() {
		return Collections.unmodifiableList(extrato);	
	}

}
