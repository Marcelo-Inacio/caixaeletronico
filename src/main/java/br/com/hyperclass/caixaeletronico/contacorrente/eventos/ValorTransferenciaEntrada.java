package br.com.hyperclass.caixaeletronico.contacorrente.eventos;

public class ValorTransferenciaEntrada extends EventoTransacional {

	public ValorTransferenciaEntrada(final Double valor) {
		super(valor, TipoEvento.TRANSFERENCIA_ENTRADA);
	}

	@Override
	public double atualizarSaldo(final Double saldo) {
		return saldo + getValor();
	}

}
