package br.com.hyperclass.caixaeletronico.domain.contacorrente.eventos;

import br.com.hyperclass.caixaeletronico.domain.contacorrente.ContaCorrente;

public class ValorTransferidoSaidaEvento extends ValorTransferidoEvento {

	public ValorTransferidoSaidaEvento(final ContaCorrente conta, final double valor) {
		super(conta, valor, TipoEvento.TRANSFERENCIA_SAIDA);
	}

	private static final long serialVersionUID = 1L;

	@Override
	public double atualizarSaldo(final double saldo) {
		return saldo - getValor();
	}

}
