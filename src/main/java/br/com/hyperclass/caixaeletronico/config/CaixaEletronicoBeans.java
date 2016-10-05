package br.com.hyperclass.caixaeletronico.config;

import java.util.LinkedList;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import br.com.hyperclass.caixaeletronico.domain.caixa.CaixaEletronico;
import br.com.hyperclass.caixaeletronico.domain.contacorrente.ContaCorrente;

/**
 * A classe <code>DomainEventsBeans</code> 
 * @author M. Inácio
 *
 */
@Configuration
public class CaixaEletronicoBeans extends AbstractCaixaEletronicoBeans {
	
	@Bean
	public CaixaEletronico caixaEletronico() {
		return new CaixaEletronico(carregarNotasCaixa(carregarNotas(), 10000), carregarContas());
	}

	@Override
	protected List<ContaCorrente> carregarContas() {
		final List<ContaCorrente> cc = new LinkedList<ContaCorrente>();
		
		cc.add(new ContaCorrente("54125-9", 10854.78));
		cc.add(new ContaCorrente("25214-8", 1050.99));
		cc.add(new ContaCorrente("88452-1", 7696.00));
		cc.add(new ContaCorrente("15935-7", 412.13));
		
		return cc;
	}	

}
