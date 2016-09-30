/*
 * @(#)EventoTransacional.java 1.0 23/09/2016
 *
 * Copyright (c) 2016, hyperCLASS. All rights reserved. hyperCLASS
 * proprietary/confidential. Use is subject to license terms.
 */

package br.com.hyperclass.caixaeletronico.domain.contacorrente.eventos;

import java.util.Date;
import java.util.EventObject;

import br.com.hyperclass.caixaeletronico.domain.contacorrente.ContaCorrente;

/**
 * A classe <code>EventoTransacional</code>EventoTransacional</code> representa
 * um evento transacional. Cada evento reside em uma lista de eventos em um
 * objeto {@link ContaCorrente}, de forma a identificar os eventos que ocorreram
 * na conta.
 *
 * @author Roberto Perillo
 * @version 1.0 23/09/2016
 */
public abstract class EventoTransacional extends EventObject implements Comparable<EventoTransacional> {

    private static final long serialVersionUID = 1L;

    public abstract double atualizarSaldo(final double saldo);

    public EventoTransacional(final ContextoEventoTransacional contexto) {
        super(contexto);
    }

    public TipoEvento getTipo() {
        return getSource().getTipo();
    }

    public Date getData() {
        return getSource().getData();
    }

    public double getValor() {
        return getSource().getValor();
    }

    @Override
    public int compareTo(final EventoTransacional outroEvento) {
        return getData().compareTo(outroEvento.getData());
    }

    @Override
    public ContextoEventoTransacional getSource() {
        return (ContextoEventoTransacional) super.getSource();
    }
}