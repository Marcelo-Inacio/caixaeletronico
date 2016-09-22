package br.com.hyperclass.caixaeletronico.restapi;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.hyperclass.caixaeletronico.caixa.CaixaEletronico;
import br.com.hyperclass.caixaeletronico.excecoes.TransacaoBancariaException;

@RestController
public class CaixaEletronicoController {
	
	@Autowired
	private CaixaEletronico caixaEletronico;

	@RequestMapping(value="/correntista/{numeroConta}", method = RequestMethod.GET)
    public String conta(@PathVariable String numeroConta) {
        try {
			caixaEletronico.entrar(numeroConta);
		} catch (TransacaoBancariaException e) {
			e.printStackTrace();
		}
        return caixaEletronico.cliente();
    }
	
	
}
