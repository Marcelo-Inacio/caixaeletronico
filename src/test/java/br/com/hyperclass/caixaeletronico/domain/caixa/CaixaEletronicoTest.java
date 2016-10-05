package br.com.hyperclass.caixaeletronico.domain.caixa;

import java.util.List;
import java.util.Map;

import br.com.hyperclass.caixaeletronico.domain.contacorrente.ContaCorrente;

public class CaixaEletronicoTest extends CaixaEletronico {

	public CaixaEletronicoTest(final Map<ValorNota, List<Nota>> notas, final List<ContaCorrente> correntistas) {
		super(notas, correntistas);
	}
	
	public ContaCorrente getConta(final String conta) throws CaixaEletronicoException {
		return getContaCorrente(conta);
	}

}
