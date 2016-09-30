/*
 * @(#)ValorInicialDisponibilizadoEvento.java 1.0 23/09/2016
 *
 * Copyright (c) 2016, hyperCLASS. All rights reserved. hyperCLASS
 * proprietary/confidential. Use is subject to license terms.
 */

package br.com.hyperclass.caixaeletronico.domain.contacorrente.eventos;

/**
 * A classe <code>ValorInicialDisponibilizadoEvento</code> representa o evento
 * de saldo inicial disponibilizado em uma conta corrente.
 *
 * @author Roberto Perillo
 * @version 1.0 23/09/2016
 */
public class ValorInicialDisponibilizadoEvento extends EventoTransacional {

    private static final long serialVersionUID = 1L;

    public ValorInicialDisponibilizadoEvento(final double valor) {
        super(new ContextoEventoTransacional(valor, TipoEvento.SALDO_INICIAL));
    }

    @Override
    public double atualizarSaldo(final double saldo) {
        return saldo + getValor();
    }
}