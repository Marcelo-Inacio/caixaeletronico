package br.com.hyperclass.caixaeletronico.restapi;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.hyperclass.caixaeletronico.caixa.CaixaEletronico;
import br.com.hyperclass.caixaeletronico.contacorrente.eventos.EventoTransacional;
import br.com.hyperclass.caixaeletronico.excecoes.TransacaoBancariaException;

@RestController
public class CaixaEletronicoController {
	
	@Autowired
	private CaixaEletronico caixaEletronico;

	@RequestMapping(value="/entrar/{numeroConta}", method = RequestMethod.GET)
	public void entrar(@PathVariable String numeroConta) {
		try {
			caixaEletronico.entrar(numeroConta);
		} catch (TransacaoBancariaException e) {
			e.printStackTrace();
		}
	}
	
	@RequestMapping(value="/sair", method = RequestMethod.GET)
    public void sair() {
		caixaEletronico.sair();        
    }
	
	@RequestMapping(value="/saldo", method = RequestMethod.GET)
    public Double conta() {
        return caixaEletronico.saldo();
    }
	
	@RequestMapping(value="/extrato", method = RequestMethod.GET)
    public List<EventoTransacional> extrato() {
        return caixaEletronico.extrato();
    }
	
	
	
}
