/*
 * @(#)ContextoEventoTransacional.java 1.0 23/09/2016
 *
 * Copyright (c) 2016, hyperCLASS. All rights reserved. hyperCLASS
 * proprietary/confidential. Use is subject to license terms.
 */

package br.com.hyperclass.caixaeletronico.domain.contacorrente.eventos;

import java.util.Date;

/**
 * A classe <code>ContextoEventoTransacional</code> contém os dados de uma
 * operação financeira. Instâncias dessa classe são encapsuladas em objetos
 * {@link EventoTransacional}.
 *
 * @author Roberto Perillo
 * @version 1.0 23/09/2016
 */
public class ContextoEventoTransacional {

    private final TipoEvento tipo;
    private final Date data;
    private final double valor;

    public ContextoEventoTransacional(final double valor, final TipoEvento tipoEvento) {
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

    public double getValor() {
        return valor;
    }
}