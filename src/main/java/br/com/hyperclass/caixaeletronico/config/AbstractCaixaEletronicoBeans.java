package br.com.hyperclass.caixaeletronico.config;

import java.util.EnumMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import br.com.hyperclass.caixaeletronico.domain.caixa.Nota;
import br.com.hyperclass.caixaeletronico.domain.caixa.ValorNota;
import br.com.hyperclass.caixaeletronico.domain.contacorrente.ContaCorrente;

public abstract class AbstractCaixaEletronicoBeans {
	
	protected abstract List<ContaCorrente> carregarContas();
	
	protected Map<ValorNota, List<Nota>> carregarNotas() {
		final Map<ValorNota, List<Nota>> notas = new EnumMap<>(ValorNota.class);
		for (ValorNota valorDaNota : ValorNota.values()) {
			notas.put(valorDaNota, new LinkedList<Nota>());
		}
		return notas;
	}
	
	protected Map<ValorNota, List<Nota>> carregarNotasCaixa(final Map<ValorNota, List<Nota>> notas, final int valor) {
		int contador = 0;
		final Map<ValorNota, List<Nota>> notasCarregadas = new EnumMap<>(ValorNota.class);
		notasCarregadas.clear();
		notasCarregadas.putAll(notas);
		while (contador != valor) {
			for (Entry<ValorNota, List<Nota>> entry : notasCarregadas.entrySet()) {
				ValorNota nota = entry.getKey();
				List<Nota> lista = entry.getValue();
				if (contador + nota.valor() <= valor) {
					lista.add(new Nota(nota));
					contador += nota.valor();
				}
			}
		}
		return notasCarregadas;
	}

}
