/*
 * @(#)ContextoTransferencia.java 1.0 23/09/2016
 *
 * Copyright (c) 2016, hyperCLASS. All rights reserved. hyperCLASS
 * proprietary/confidential. Use is subject to license terms.
 */

package br.com.hyperclass.caixaeletronico.domain.contacorrente.eventos;

import br.com.hyperclass.caixaeletronico.domain.contacorrente.ContaCorrente;

/**
 * A classe <code>ContextoTransferencia</code> é um contexto de evento
 * transacional que contém também a conta corrente. A conta corrente contida em
 * instâncias dessa classe é tanto a conta que recebeu a transferência quanto a
 * conta que a efetuou.
 *
 * @author Roberto Perillo
 * @version 1.0 23/09/2016
 */
public class ContextoTransferencia extends ContextoEventoTransacional {

    private final ContaCorrente conta;

    public ContextoTransferencia(final ContaCorrente conta, final double valor, final TipoEvento tipoEvento) {
        super(valor, tipoEvento);
        this.conta = conta;
    }

    public ContaCorrente getConta() {
        return conta;
    }
    
    public String getNumeroConta() {
    	return conta.getNumeroConta();
    }
}