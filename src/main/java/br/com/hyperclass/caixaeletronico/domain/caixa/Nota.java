/*
 * @(#)Nota.java 1.0 23/09/2016
 *
 * Copyright (c) 2016, hyperCLASS. All rights reserved. hyperCLASS
 * proprietary/confidential. Use is subject to license terms.
 */

package br.com.hyperclass.caixaeletronico.domain.caixa;

/**
 * A classe <code>Nota</code> representa uma nota (i.e., uma cédula de
 * dinheiro).
 *
 * @author Roberto Perillo
 * @version 1.0 23/09/2016
 */
public class Nota {

    private final ValorNota valor;

    public Nota(final ValorNota valor) {
        this.valor = valor;
    }

    public int getValor() {
        return valor.valor();
    }
}