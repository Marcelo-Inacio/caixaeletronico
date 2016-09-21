package br.com.hyperclass.caixaeletronico.excecoes;

public class SaldoInsuficienteException extends TransacaoBancariaException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public SaldoInsuficienteException() {
		super("Saldo Insuficiente");
	}

}
