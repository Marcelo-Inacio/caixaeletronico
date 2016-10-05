package br.com.hyperclass.caixaeletronico.config;

import java.util.EnumMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import br.com.hyperclass.caixaeletronico.domain.contacorrente.eventos.TipoEvento;
import br.com.hyperclass.caixaeletronico.restapi.serializer.Serializer;

@Configuration
public class SerializersConfig {

	@Bean
	public Map<TipoEvento, Serializer> mapaSerializer(@Autowired @Qualifier("defaultEventoSerializer") final Serializer defaultSerializer,
			@Autowired @Qualifier("transferenciaSerializer") final Serializer transferenciaSerializer) {
		final Map<TipoEvento, Serializer> mapa = new EnumMap<>(TipoEvento.class);
		mapa.put(TipoEvento.SALDO_INICIAL, defaultSerializer);
		mapa.put(TipoEvento.SAQUE, defaultSerializer);
		mapa.put(TipoEvento.DEPOSITO, defaultSerializer);
		mapa.put(TipoEvento.TRANSFERENCIA_ENTRADA, transferenciaSerializer);
		mapa.put(TipoEvento.TRANSFERENCIA_SAIDA, transferenciaSerializer);
		return mapa;
	}
}