package br.com.hyperclass.caixaeletronico.excecoes;

public class DepositoException extends CaixaEletronicoException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public DepositoException() {
		super("Problema ao realizar depósito");
	}

}
