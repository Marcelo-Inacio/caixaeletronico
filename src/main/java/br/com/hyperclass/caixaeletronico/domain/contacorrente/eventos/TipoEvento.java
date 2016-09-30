/*
 * @(#)TipoEvento.java 1.0 23/09/2016
 *
 * Copyright (c) 2016, hyperCLASS. All rights reserved. hyperCLASS
 * proprietary/confidential. Use is subject to license terms.
 */

package br.com.hyperclass.caixaeletronico.domain.contacorrente.eventos;

/**
 * A enumeração <code>TipoEvento</code> contém os tipos de eventos transacionais
 * que podem ocorrer na aplicação.
 *
 * @author Roberto Perillo
 * @version 1.0 23/09/2016
 */
public enum TipoEvento {

    SAQUE, TRANSFERENCIA_ENTRADA, TRANSFERENCIA_SAIDA, DEPOSITO, SALDO_INICIAL;
}