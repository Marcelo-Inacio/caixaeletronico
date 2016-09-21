package br.com.hyperclass.caixaeletronico.excecoes;

public class TransferenciaException extends TransacaoBancariaException {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public TransferenciaException() {
		super("Problema ao relizar transferência");
	}

}
