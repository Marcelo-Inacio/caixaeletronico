/*
 * @(#)CaixaEletronico.java 1.0 23/09/2016
 *
 * Copyright (c) 2016, hyperCLASS. All rights reserved. hyperCLASS
 * proprietary/confidential. Use is subject to license terms.
 */

package br.com.hyperclass.caixaeletronico.domain.caixa;

import java.util.EnumMap;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import br.com.hyperclass.caixaeletronico.domain.contacorrente.ContaCorrente;
import br.com.hyperclass.caixaeletronico.domain.contacorrente.eventos.EventoTransacional;

/**
 * A classe <code>CaixaEletronico</code> contém as operações de transações
 * financeiras disponíveis na aplicação. Cada operação atua sobre uma conta
 * corrente específica, dado o seu número em cada operação.
 *
 * @author Roberto Perillo
 * @version 1.0 23/09/2016
 */

public class CaixaEletronico {

    private final Map<String, ContaCorrente> contasClientes = new HashMap<>();
    private final Map<ValorNota, List<Nota>> notas = new EnumMap<>(ValorNota.class);

    public CaixaEletronico(final Map<ValorNota, List<Nota>> notas, final List<ContaCorrente> correntistas) {
        carregarContasClientes(correntistas);
        this.notas.putAll(notas);
    }
    
    public void entrar(final String contaCorrente) throws ContaInvalidaException {
    	final ContaCorrente conta = contasClientes.get(contaCorrente);
    	if (conta == null) {
            throw new ContaInvalidaException(contaCorrente);
        }
    }

    public double saldo(final String contaCorrente) throws CaixaEletronicoException {
        final ContaCorrente conta = contasClientes.get(contaCorrente);
        if (conta == null) {
            throw new ContaInvalidaException(contaCorrente);
        }
        return conta.saldo();
    }

    public void sacar(final String conta, final double valor) throws CaixaEletronicoException {
        final ContaCorrente contaCorrente = getContaCorrente(conta);
        if (verificarNotas(valor)) {
            contaCorrente.sacar(valor);
            removerNotas(valor);
        } else {
            throw new NotasIndisponiveisException();
        }
    }

    public void depositar(final String conta, final double valor) throws CaixaEletronicoException {
        final ContaCorrente contaCorrente = getContaCorrente(conta);
        contaCorrente.creditar(valor);
    }

    public void transferir(final String contaOrigem, final String contaDestino, final Double valor) throws CaixaEletronicoException {
        final ContaCorrente origem = getContaCorrente(contaOrigem);
        final ContaCorrente destino = getContaCorrente(contaDestino);
        origem.transferir(destino, valor);
    }
    
    public List<EventoTransacional> extrato(final String conta) throws CaixaEletronicoException {
    	final ContaCorrente contaCorrente = getContaCorrente(conta);
    	return contaCorrente.extrato();
    }

    /**
     * realiza uma varredura nas notas do caixa e retorna a lista de notas
     * disponíveis
     *
     * @return
     */
    public Map<ValorNota, List<Nota>> notasDisponiveis() {
        return new EnumMap<>(notas);
    }

    private void removerNotas(final double valorSacar) {
        double valorRemover = 0;
        for (final Entry<ValorNota, List<Nota>> entry : notas.entrySet()) {
            final ValorNota nota = entry.getKey();
            final LinkedList<Nota> lista = (LinkedList<Nota>) entry.getValue();
            while ((valorRemover + nota.valor()) <= valorSacar && !lista.isEmpty()) {
                valorRemover = valorRemover + nota.valor();
                lista.removeLast();
            }
        }
    }

    /**
     * Algoritmo que verica se tem notas disponíveis no caixa eletrônico
     * recebendo um valor a ser removido como parâmetro. Auxilia método
     * sacar.
     *
     * @param valor
     * @return
     */
    private boolean verificarNotas(final double valor) {
        double somaNotas = 0.0;
        final Map<ValorNota, List<Nota>> notasMapCopia = new EnumMap<>(ValorNota.class);
        notasMapCopia.putAll(notas);
              
        for (final Map.Entry<ValorNota, List<Nota>> entry : notasMapCopia.entrySet()) {
            final ValorNota nota = entry.getKey();
            final LinkedList<Nota> lista = (LinkedList<Nota>) entry.getValue();
            while ((somaNotas < valor) && (somaNotas + nota.valor() <= valor) && !lista.isEmpty()) {
                somaNotas += nota.valor();
                lista.removeLast();
            }
        }

        return somaNotas == valor ? true : false;
    }

    /**
     * realiza mapeamento das contas dos correntistas
     *
     * @param correntistas
     */
    private void carregarContasClientes(final List<ContaCorrente> correntistas) {
        for (final ContaCorrente cc : correntistas) {
            contasClientes.put(cc.getNumeroConta(), cc);
        }
    }

    private ContaCorrente getContaCorrente(final String contaCorrente) throws CaixaEletronicoException {
        final ContaCorrente conta = contasClientes.get(contaCorrente);
        if (conta == null) {
            throw new ContaInvalidaException(contaCorrente);
        }
        return conta;
    }
}