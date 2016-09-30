/*
 * @(#)SaldoInsuficienteException.java 1.0 23/09/2016
 *
 * Copyright (c) 2016, hyperCLASS. All rights reserved. hyperCLASS
 * proprietary/confidential. Use is subject to license terms.
 */

package br.com.hyperclass.caixaeletronico.domain.contacorrente;

import br.com.hyperclass.caixaeletronico.domain.caixa.CaixaEletronicoException;

/**
 * A classe <code>SaldoInsuficienteException</code> representa a situação onde
 * tentou-se realizar uma operação financeira e não houve saldo suficiente para
 * finalizá-la com sucesso.
 *
 * @author Roberto Perillo
 * @version 1.0 23/09/2016
 */
public class SaldoInsuficienteException extends CaixaEletronicoException {

    private static final long serialVersionUID = 1L;

    private final double saldo;

    public SaldoInsuficienteException(final double saldo) {
        super();
        this.saldo = saldo;
    }

    public double getSaldo() {
        return saldo;
    }
}