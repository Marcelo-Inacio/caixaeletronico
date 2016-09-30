/*
 * @(#)ContaInvalidaException.java 1.0 23/09/2016
 *
 * Copyright (c) 2016, hyperCLASS. All rights reserved. hyperCLASS
 * proprietary/confidential. Use is subject to license terms.
 */

package br.com.hyperclass.caixaeletronico.domain.caixa;

/**
 * A classe <code>ContaInvalidaException</code> representa a situação onde um
 * número de conta informado não é válido.
 *
 * @author Roberto Perillo
 * @version 1.0 23/09/2016
 */
public class ContaInvalidaException extends CaixaEletronicoException {

    private static final long serialVersionUID = 1L;

    private final String conta;

    public ContaInvalidaException(final String conta) {
        super();
        this.conta = conta;
    }

    public String getConta() {
        return conta;
    }
}