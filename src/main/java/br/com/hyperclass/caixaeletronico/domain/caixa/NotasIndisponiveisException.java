/*
 * @(#)NotasIndisponiveisException.java 1.0 23/09/2016
 *
 * Copyright (c) 2016, hyperCLASS. All rights reserved. hyperCLASS
 * proprietary/confidential. Use is subject to license terms.
 */

package br.com.hyperclass.caixaeletronico.domain.caixa;

/**
 * A classe <code>NotasIndisponiveisException</code> representa a situação onde
 * não há notas disponíveis no caixa eletrônico para a conclusão de um saque.
 *
 * @author Roberto Perillo
 * @version 1.0 23/09/2016
 */
public class NotasIndisponiveisException extends CaixaEletronicoException {

    private static final long serialVersionUID = 1L;

    private final Nota[] notas;

    public NotasIndisponiveisException(final Nota... notas) {
        super();
        this.notas = notas;
    }

    public Nota[] getNotas() {
        return notas;
    }
}