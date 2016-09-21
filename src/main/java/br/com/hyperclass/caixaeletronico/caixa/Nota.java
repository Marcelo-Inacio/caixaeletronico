package br.com.hyperclass.caixaeletronico.caixa;

public final class Nota {
	
	private final ValorNota valor;

	public Nota(final ValorNota valor) {
		this.valor = valor;
	}
	
	
	public Integer getValor(){
		return valor.valorNumeral();
	}
	
	public ValorNota getValorNota(){
		return valor;
	}
	
}
