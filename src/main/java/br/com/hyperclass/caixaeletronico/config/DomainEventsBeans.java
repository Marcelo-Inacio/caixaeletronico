package br.com.hyperclass.caixaeletronico.config;

import java.util.LinkedList;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import br.com.hyperclass.caixaeletronico.caixa.CaixaEletronico;
import br.com.hyperclass.caixaeletronico.contacorrente.ContaCorrente;


@Configuration
public class DomainEventsBeans {
	
	@Bean
	public CaixaEletronico listener() {
		final CaixaEletronico caixa = new CaixaEletronico(5000, contas());
		return caixa;
	}
	
	private List<ContaCorrente> contas() {
		List<ContaCorrente> cc = new LinkedList<ContaCorrente>();
		
		cc.add(new ContaCorrente("João da Silva", "54125-9", 10854.78));
		cc.add(new ContaCorrente("Pedro Otávio Magalhães", "25214-8", 1050.99));
		cc.add(new ContaCorrente("Maria Green", "88452-1", 7696.00));
		cc.add(new ContaCorrente("Stephan Pereira", "15935-7", 412.13));
		
		return cc;
	}

}
