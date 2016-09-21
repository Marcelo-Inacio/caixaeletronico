package br.com.hyperclass.caixaeletronico.excecoes;

public abstract class TransacaoBancariaException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	TransacaoBancariaException(final String mensagem) {
		super(mensagem);
	}
	

}
