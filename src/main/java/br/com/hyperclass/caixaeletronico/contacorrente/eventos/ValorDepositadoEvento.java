package br.com.hyperclass.caixaeletronico.contacorrente.eventos;

public class ValorDepositadoEvento extends EventoTransacional {

	public ValorDepositadoEvento(final Double valor) {
		super(valor, TipoEvento.DEPOSITO);
	}

	@Override
	public double atualizarSaldo(final Double saldo) {
		//saldo = saldo + getValor();
		return saldo + getValor();
	}

}
