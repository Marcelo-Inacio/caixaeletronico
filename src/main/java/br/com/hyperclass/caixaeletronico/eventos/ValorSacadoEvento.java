package br.com.hyperclass.caixaeletronico.eventos;

public class ValorSacadoEvento extends EventoTransacional {

	public ValorSacadoEvento(final Double valor) {
		super(valor, TipoEvento.SAQUE);
	}

	@Override
	public double atualizarSaldo(final Double saldo) {
		//saldo = saldo - getValor();
		return saldo - getValor();
	}

}
