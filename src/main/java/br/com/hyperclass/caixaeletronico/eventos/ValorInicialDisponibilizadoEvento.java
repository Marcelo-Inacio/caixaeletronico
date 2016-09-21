package br.com.hyperclass.caixaeletronico.eventos;

public class ValorInicialDisponibilizadoEvento extends EventoTransacional {

	public ValorInicialDisponibilizadoEvento(final Double valor) {
		super(valor, TipoEvento.SALDO_INICIAL);
	}

	@Override
	public double atualizarSaldo(final Double saldo) {
		//saldo = saldo + getValor();
		return saldo + getValor();
	}

}
