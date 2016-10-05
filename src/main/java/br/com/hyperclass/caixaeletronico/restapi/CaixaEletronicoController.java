/*
 * @(#)CaixaEletronico.java 1.0 27/09/2016
 *
 * Copyright (c) 2016, hyperCLASS. All rights reserved. hyperCLASS
 * proprietary/confidential. Use is subject to license terms.
 */
package br.com.hyperclass.caixaeletronico.restapi;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.hyperclass.caixaeletronico.domain.caixa.CaixaEletronico;
import br.com.hyperclass.caixaeletronico.domain.caixa.CaixaEletronicoException;
import br.com.hyperclass.caixaeletronico.restapi.wrapper.ExtratoWrapper;
import br.com.hyperclass.caixaeletronico.restapi.wrapper.NotasDisponiveisWrapper;
import br.com.hyperclass.caixaeletronico.restapi.wrapper.TransferenciaWrapper;
import br.com.hyperclass.caixaeletronico.restapi.wrapper.ValorWrapper;
/**
 * A classe <code>CaixaEletronicoController</code> contém as rotas de operações
 * financeiras disponíveis na aplicação. Cada rota atua sobre uma funcionalidade do
 * caixa eletronico, dado o seu número de conta em cada operação.
 *
 * @author Marcelo Inácio
 * @version 1.0 27/09/2016
 */

@RestController
public class CaixaEletronicoController {
	
	@Autowired
	@Qualifier("caixaEletronicoTest")
	private CaixaEletronico caixaEletronico;

	@RequestMapping(value = "/{conta}/saque", method = RequestMethod.POST)
	public ResponseEntity<ValorWrapper> sacar(@PathVariable("conta") String conta,
			@RequestBody final ValorWrapper valorWrapper) throws CaixaEletronicoException {
		caixaEletronico.sacar(conta, valorWrapper.getValor());
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	@RequestMapping(value = "/{conta}/deposito", method = RequestMethod.POST)
	public ResponseEntity<ValorWrapper> depositar(@PathVariable("conta") String numeroConta, 
			@RequestBody final ValorWrapper valorWrapper) throws CaixaEletronicoException {
		caixaEletronico.depositar(numeroConta, valorWrapper.getValor());
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	@RequestMapping(value = "/{contaOrigem}/transferencia", method = RequestMethod.POST)
	public ResponseEntity<TransferenciaWrapper> transferencia(@PathVariable("contaOrigem") final String contaOrigem, 
			@RequestBody final TransferenciaWrapper transferenciaWrapper) throws CaixaEletronicoException {
		caixaEletronico.transferir(contaOrigem, transferenciaWrapper.getContaDestino(), transferenciaWrapper.getValor());
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	@RequestMapping(value = "/{conta}/extrato", method = RequestMethod.GET)
	public ResponseEntity<ExtratoWrapper> extrato(@PathVariable("conta") String conta) throws CaixaEletronicoException {
		return new ResponseEntity<>(new ExtratoWrapper(caixaEletronico.extrato(conta)), HttpStatus.OK);
	}
	
	@RequestMapping(value = "/{conta}/saldo", method = RequestMethod.GET)
	public ResponseEntity<ValorWrapper> saldo(@PathVariable("conta") String conta) throws CaixaEletronicoException {
		return new ResponseEntity<>(new ValorWrapper(caixaEletronico.saldo(conta)), HttpStatus.OK);
	}
	
	@RequestMapping(value = "/notasDisponiveis", method = RequestMethod.GET)
	public ResponseEntity<NotasDisponiveisWrapper> notasDisponiveis() {
		return new ResponseEntity<>(new NotasDisponiveisWrapper(caixaEletronico.notasDisponiveis()), HttpStatus.OK);
	}
	
	@RequestMapping(value = "/{conta}/entrar", method = RequestMethod.GET)
	public ResponseEntity<?> entrar(@PathVariable("conta") final String conta) throws CaixaEletronicoException {
		caixaEletronico.entrar(conta);
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	
}
