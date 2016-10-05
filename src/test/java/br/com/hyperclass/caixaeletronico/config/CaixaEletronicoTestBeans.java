package br.com.hyperclass.caixaeletronico.config;

import java.util.LinkedList;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import br.com.hyperclass.caixaeletronico.domain.caixa.CaixaEletronicoTest;
import br.com.hyperclass.caixaeletronico.domain.contacorrente.ContaCorrente;

@Configuration
public class CaixaEletronicoTestBeans extends AbstractCaixaEletronicoBeans {
	
	@Bean
	public CaixaEletronicoTest caixaEletronicoTest() {
		return new CaixaEletronicoTest(carregarNotasCaixa(carregarNotas(), 10000), carregarContas());
	}
	
	@Override
	protected List<ContaCorrente> carregarContas() {
		final List<ContaCorrente> cc = new LinkedList<ContaCorrente>();
		cc.add(new ContaCorrente("12345-0", 0.0));		
		return cc;
	}

}
