package br.com.hyperclass.caixaeletronico.excecoes;

public class ContaInvalidaException extends TransacaoBancariaException {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ContaInvalidaException() {
		super("Conta Inválida");
	}

}
