package br.com.hyperclass.caixaeletronico.caixa;

public enum ValorNota {
	
	CEM(100),
	CINQUENTA(50),
	VINTE(20),
	DEZ(10);
	
	private final Integer valor;
	
	private ValorNota(final Integer valor) {
		this.valor = valor;
	}
	
	public Integer valorNumeral(){
		return valor;
	}

}
