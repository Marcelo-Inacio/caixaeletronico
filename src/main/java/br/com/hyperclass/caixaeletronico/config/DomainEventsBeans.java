package br.com.hyperclass.caixaeletronico.config;

import java.util.EnumMap;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import br.com.hyperclass.caixaeletronico.domain.caixa.CaixaEletronico;
import br.com.hyperclass.caixaeletronico.domain.caixa.Nota;
import br.com.hyperclass.caixaeletronico.domain.caixa.ValorNota;
import br.com.hyperclass.caixaeletronico.domain.contacorrente.ContaCorrente;
import br.com.hyperclass.caixaeletronico.domain.contacorrente.eventos.TipoEvento;
import br.com.hyperclass.caixaeletronico.restapi.serializer.Serializer;

/**
 * A classe <code>DomainEventsBeans</code> 
 * @author M. Inácio
 *
 */
@Configuration
public class DomainEventsBeans {
	
	@Bean
	public Map<TipoEvento, Serializer> mapaSerializer(@Autowired @Qualifier("defaultEventoSerializer") final Serializer defaultSerializer,
			@Autowired @Qualifier("transferenciaSerializer")final Serializer transferenciaSerializer) {
		final Map<TipoEvento, Serializer> mapa = new HashMap<>();
		mapa.put(TipoEvento.SALDO_INICIAL, defaultSerializer);
		mapa.put(TipoEvento.SAQUE, defaultSerializer);
		mapa.put(TipoEvento.DEPOSITO, defaultSerializer);
		mapa.put(TipoEvento.TRANSFERENCIA_ENTRADA, transferenciaSerializer);
		mapa.put(TipoEvento.TRANSFERENCIA_SAIDA, transferenciaSerializer);
		return mapa;
	}

	@Bean
	public CaixaEletronico caixaEletronico() {
		return new CaixaEletronico(carregarNotasCaixa(carregarNotas(), 10000), carregarContas());
	}

	private List<ContaCorrente> carregarContas() {
		final List<ContaCorrente> cc = new LinkedList<ContaCorrente>();
		
		cc.add(new ContaCorrente("54125-9", 10854.78));
		cc.add(new ContaCorrente("25214-8", 1050.99));
		cc.add(new ContaCorrente("88452-1", 7696.00));
		cc.add(new ContaCorrente("15935-7", 412.13));
		
		return cc;
	}
	
	private Map<ValorNota, List<Nota>> carregarNotas() {
		final Map<ValorNota, List<Nota>> notas = new EnumMap<>(ValorNota.class);
		for (ValorNota valorDaNota : ValorNota.values()) {
			notas.put(valorDaNota, new LinkedList<Nota>());
		}
		return notas;
	}
	
	private Map<ValorNota, List<Nota>> carregarNotasCaixa(final Map<ValorNota, List<Nota>> notas, final int valor) {
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
