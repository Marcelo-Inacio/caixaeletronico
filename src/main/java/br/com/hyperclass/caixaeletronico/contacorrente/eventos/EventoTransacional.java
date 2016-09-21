package br.com.hyperclass.caixaeletronico.contacorrente.eventos;

import java.util.Date;

public abstract class EventoTransacional implements Comparable<EventoTransacional>{
	
	private TipoEvento tipo;
	private Date data;
	private Double valor;
	
	public EventoTransacional(final Double valor, final TipoEvento tipoEvento) {
		this.data = new Date();
		this.valor = valor;
		this.tipo = tipoEvento;
	}
	
	public TipoEvento getTipo() {
		return tipo;
	}
	
	public Date getData() {
		return new Date(data.getTime());
	}
	
	public Double getValor() {
		return valor;
	}
	
	protected void setTipo(final TipoEvento tipo) {
		this.tipo = tipo;
	}
	
	public abstract double atualizarSaldo (final Double saldo);

	@Override
	public int compareTo(final EventoTransacional outroEvento) {
		return data.compareTo(outroEvento.getData()); 
	}
	
	
}
