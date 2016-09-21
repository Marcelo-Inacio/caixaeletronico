package br.com.hyperclass.caixaeletronico.excecoes;

public class NotasIndisponiveisException extends TransacaoBancariaException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public NotasIndisponiveisException() {
		super("Notas Indisponíveis no momento");
	}

}
