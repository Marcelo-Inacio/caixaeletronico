/**
 * Date: 09/08/2016
 * Developed by: Inácio.
 * 
 * last update: 21/09/2016
 * */
package br.com.hyperclass.caixaeletronico;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import br.com.hyperclass.caixaeletronico.excecoes.ContaInvalidaException;
import br.com.hyperclass.caixaeletronico.excecoes.DepositoException;
import br.com.hyperclass.caixaeletronico.excecoes.NotasIndisponiveisException;
import br.com.hyperclass.caixaeletronico.excecoes.TransferenciaException;



public class CaixaEletronico {
	
	private final Map<String, ContaCorrente> contasClientes = new HashMap<>();
	private final Map<ValorNota, List<Nota>> notasMap = new HashMap<>();
	private ContaCorrente clienteLogado;
	
	/**
	 * inicializa o caixa eletrônico definindo quantidade de dinheiro inicial e as contas dos clientes
	 * 
	 * @param quantidadeDinheiro
	 * @param correntistas
	 */
	public CaixaEletronico(final Integer quantidadeDinheiro, final List<ContaCorrente> correntistas) {
		carregarContasClientes(correntistas);
		mapearNotas();
		carregarNotasCaixa(quantidadeDinheiro);
	}
	
	public Double saldo() {
		return clienteLogado.saldo();
	}
	
	public void sacar(final Double valor) {
		if (verificarNotas(valor)) {
			clienteLogado.debitar(valor);
			removerNotas(valor);
		} else {
			throw new NotasIndisponiveisException();
		}
	}
		
	public void depositar(final String numeroConta, final Double valor) {
		if (!validarConta(numeroConta)){
			throw new DepositoException();
		}
		contasClientes.get(numeroConta).creditar(valor);
	}
	
	public void transferir(final String numeroContaDestino, final Double valor) {
		if (!validarConta(numeroContaDestino)) {
			throw new TransferenciaException();
		}
		clienteLogado.transferirPara(contasClientes.get(numeroContaDestino), valor);
	}
	
	public void entrar(final String numeroConta) {
		if (!validarConta(numeroConta)) {
			throw new ContaInvalidaException();
		}
		clienteLogado = contasClientes.get(numeroConta);
	}
	
	public void sair() {
		clienteLogado = null;
	}
	
	public String cliente() {
		return clienteLogado.getNome();
	}
	
	/**
	 * realiza uma varredura nas notas do caixa e retorna a lista de notas disponíveis
	 * 
	 * @return
	 */
	public List<ValorNota> notasDisponiveis() {
		List<ValorNota> listaNotas = new ArrayList<>();
		for (Entry<ValorNota, List<Nota>> entry : notasMap.entrySet()) {
	    	ValorNota nota = entry.getKey();
	    	List<Nota> lista = entry.getValue();
	    	if(!lista.isEmpty()){
	    		listaNotas.add(nota);
	    	}
	    }
		return listaNotas;
	}
	
	/**
	 * algoritimo que realiza um mapeamento(uma carga) das notas no caixa eletrônico para demais operações
	 *  
	 * @param valor
	 */
	private void carregarNotasCaixa(final Integer valor) {
		
		Integer contador = new Integer("0");
		
		while (!contador.equals(valor)) {		
		    for (Entry<ValorNota, List<Nota>> entry : notasMap.entrySet()) {
		    	ValorNota nota = entry.getKey();
		    	List<Nota> lista = entry.getValue();
		    	if (contador + nota.valorNumeral() <= valor) {
		    		lista.add(new Nota(nota));
			    	contador += nota.valorNumeral();
		    	}
		    }
		}
	}
	
	/**
	 * Auxilia método sacar, removendo as notas do caixa após realizar
	 * a verificação com método <code>verificarNotas()</code>
	 * @param valorSacar
	 */
	private void removerNotas(final Double valorSacar) {
		Double valorRemover = new Double("0");
		for (Entry<ValorNota, List<Nota>> entry : notasMap.entrySet()) {
	    	ValorNota nota = entry.getKey();
	    	LinkedList<Nota> lista = (LinkedList<Nota>) entry.getValue();
	    	while((valorRemover + nota.valorNumeral()) <= valorSacar && !lista.isEmpty()) {
	    		valorRemover = valorRemover + nota.valorNumeral();
	    		lista.removeLast();
	    	}
	    }
	}
	
	/**
	 * Algoritmo que verica se há notas disponíveis no caixa eletrônico recebendo um valor a ser removido
	 * como parâmetro.
	 * Auxilia método sacar. 
	 * @param valor
	 * @return
	 */
	private Boolean verificarNotas(final Double valor) {
		Double somaNotas = new Double("0");
		final Map <ValorNota, List<Nota>> notasMapCopia = new HashMap<>(notasMap);
		for (Entry<ValorNota, List<Nota>> entry : notasMapCopia.entrySet()) {
	    	ValorNota nota = entry.getKey();
	    	LinkedList<Nota> lista = (LinkedList<Nota>) entry.getValue();
	    	while((somaNotas < valor) && (somaNotas + nota.valorNumeral() <= valor) && !lista.isEmpty()) {
	    		somaNotas = somaNotas + nota.valorNumeral();
	    		lista.removeLast();
	    	}
	    }	
		return somaNotas.equals(valor) ? true : false;
	}
	
	/**
	 * verifica se existe o cliente
	 * @param numeroConta
	 * @return
	 */
	private Boolean validarConta(final String numeroConta) {
		 return contasClientes.containsKey(numeroConta);
	}
	
	/**
	 * realiza mapeamento das contas dos correntistas
	 * @param correntistas
	 */
	private void carregarContasClientes(final List<ContaCorrente> correntistas) {
		for (ContaCorrente cc : correntistas) {
			contasClientes.put(cc.getNumeroConta(), cc);
		}
	}
	
	/**
	 * realiza mapeamento das notas existentes no mercado atual
	 * através do ENUM <class>ValorNota</class>
	 */
	private void mapearNotas() {
		for (ValorNota valorDaNota : ValorNota.values()) {
			notasMap.put(valorDaNota, new LinkedList<Nota>());
		}
	}
	
	/*	//descobre o valor contido no caixa eletronico
	private Double valorCaixa() {
		Double valor = new Double("0");
		for (Entry<ValorNota, List<Nota>> entry : notasMap.entrySet()) {
	    	ValorNota nota = entry.getKey();
	    	List<Nota> lista = entry.getValue();
	    	valor += (nota.valorNumeral() * lista.size());
	    }		
		return valor;
	}
	 */

}
