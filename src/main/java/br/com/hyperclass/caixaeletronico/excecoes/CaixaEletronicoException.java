package br.com.hyperclass.caixaeletronico.excecoes;

public abstract class CaixaEletronicoException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	CaixaEletronicoException(final String mensagem) {
		super(mensagem);
	}
	

}
