/*
 * @(#)ValorDepositadoEvento.java 1.0 23/09/2016
 *
 * Copyright (c) 2016, hyperCLASS. All rights reserved. hyperCLASS
 * proprietary/confidential. Use is subject to license terms.
 */

package br.com.hyperclass.caixaeletronico.domain.contacorrente.eventos;

/**
 * A classe <code>ValorDepositadoEvento</code> representa um evento de depósito,
 * ocorrido em uma conta corrente.
 *
 * @author Roberto Perillo
 * @version 1.0 23/09/2016
 */
public class ValorDepositadoEvento extends EventoTransacional {

    private static final long serialVersionUID = 1L;

    public ValorDepositadoEvento(final double valor) {
        super(new ContextoEventoTransacional(valor, TipoEvento.DEPOSITO));
    }

    @Override
    public double atualizarSaldo(final double saldo) {
        return saldo + getValor();
    }
}