/*
 * @(#)ValorNota.java 1.0 23/09/2016
 *
 * Copyright (c) 2016, hyperCLASS. All rights reserved. hyperCLASS
 * proprietary/confidential. Use is subject to license terms.
 */

package br.com.hyperclass.caixaeletronico.domain.caixa;

/**
 * A enumeração <code>ValorNota</code> contém os possíveis valores que podem ser
 * assumidos por notas na aplicação.
 *
 * @author Roberto Perillo
 * @version 1.0 23/09/2016
 */
public enum ValorNota {

    CEM(100), CINQUENTA(50), VINTE(20), DEZ(10), CINCO(5), DOIS(2), UM(1);

    private final int valor;

    private ValorNota(final int valor) {
        this.valor = valor;
    }

    public int valor() {
        return valor;
    }
}