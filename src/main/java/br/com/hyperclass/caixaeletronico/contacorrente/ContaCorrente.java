package br.com.hyperclass.caixaeletronico.contacorrente;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import br.com.hyperclass.caixaeletronico.contacorrente.eventos.EventoTransacional;
import br.com.hyperclass.caixaeletronico.contacorrente.eventos.ValorDepositadoEvento;
import br.com.hyperclass.caixaeletronico.contacorrente.eventos.ValorInicialDisponibilizadoEvento;
import br.com.hyperclass.caixaeletronico.contacorrente.eventos.ValorSacadoEvento;
import br.com.hyperclass.caixaeletronico.contacorrente.eventos.ValorTransferenciaEntrada;
import br.com.hyperclass.caixaeletronico.contacorrente.eventos.ValorTransferenciaSaida;
import br.com.hyperclass.caixaeletronico.excecoes.SaldoInsuficienteException;
import br.com.hyperclass.caixaeletronico.excecoes.TransacaoBancariaException;


public class ContaCorrente {
	
	private final String nomeCliente;
	private final String numeroConta;
	private final List<EventoTransacional> historico = new LinkedList<>();
	
	public ContaCorrente(final String nomeCliente, final String numeroConta, final Double saldoInicial) {
		this.nomeCliente = nomeCliente;
		this.numeroConta = numeroConta;
		disponibilizarSaldoInicial(saldoInicial);
	}
	
	public Double saldo() {
		Double saldo = new Double("0");
		for (EventoTransacional evento : historico) {
			saldo = evento.atualizarSaldo(saldo);
		}
		return saldo;
	}
	
	public Boolean haSaldo(final Double valor) {
		return saldo() >= valor;
	}
	
	public List<EventoTransacional> extrato() {
		Collections.sort(historico);
		return Collections.unmodifiableList(historico);
	}
	
	private void disponibilizarSaldoInicial(final Double valor) {
		historico.add(new ValorInicialDisponibilizadoEvento(valor));
	}
	
	public void creditar(final Double valor) {
		historico.add(new ValorDepositadoEvento(valor));
	}
	
	public void debitar(final Double valor) throws TransacaoBancariaException {
		if(!haSaldo(valor)) {
			throw new SaldoInsuficienteException();
		}
		historico.add(new ValorSacadoEvento(valor));
	}
	
	public void transferirPara(final ContaCorrente contaDestino, final Double valor) throws TransacaoBancariaException {
		if(!haSaldo(valor)) {
			throw new SaldoInsuficienteException();
		}
		historico.add(new ValorTransferenciaSaida(valor));
		contaDestino.transferirDe(valor);
	}
	
	public void transferirDe(final Double valor) {
		historico.add(new ValorTransferenciaEntrada(valor));
	}
		
	public String getNome() {
		return nomeCliente;
	}
	
	public String getNumeroConta() {
		return numeroConta;
	}
	
}
