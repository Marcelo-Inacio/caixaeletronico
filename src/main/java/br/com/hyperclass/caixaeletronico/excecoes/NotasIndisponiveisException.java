package br.com.hyperclass.caixaeletronico.excecoes;

public class NotasIndisponiveisException extends CaixaEletronicoException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public NotasIndisponiveisException() {
		super("Notas Indispon�veis no momento");
	}

}
