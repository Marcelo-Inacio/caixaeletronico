package br.com.hyperclass.caixaeletronico.contacorrente.eventos;

public class ValorTransferenciaSaida extends EventoTransacional {

	public ValorTransferenciaSaida(final Double valor) {
		super(valor, TipoEvento.TRANSFERENCIA_SAIDA);
	}

	@Override
	public double atualizarSaldo(final Double saldo) {
		//saldo = saldo - getValor();
		return saldo - getValor();
	}

}
