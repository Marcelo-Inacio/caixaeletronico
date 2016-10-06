package br.com.hyperclass.caixaeletronico.test;

import static org.junit.Assert.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.DefaultMockMvcBuilder;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.hyperclass.caixaeletronico.config.SpringContextConfigurationTest;
import br.com.hyperclass.caixaeletronico.config.WebConfig;
import br.com.hyperclass.caixaeletronico.domain.caixa.CaixaEletronicoTest;
import br.com.hyperclass.caixaeletronico.domain.contacorrente.ContaCorrente;
import br.com.hyperclass.caixaeletronico.domain.contacorrente.eventos.EventoTransacional;
import br.com.hyperclass.caixaeletronico.domain.contacorrente.eventos.ValorDepositadoEvento;
import br.com.hyperclass.caixaeletronico.domain.contacorrente.eventos.ValorInicialDisponibilizadoEvento;
import br.com.hyperclass.caixaeletronico.domain.contacorrente.eventos.ValorSacadoEvento;
import br.com.hyperclass.caixaeletronico.restapi.wrapper.ExtratoWrapper;
import br.com.hyperclass.caixaeletronico.restapi.wrapper.TransferenciaWrapper;
import br.com.hyperclass.caixaeletronico.restapi.wrapper.ValorWrapper;
import br.com.hyperclass.caixaeletronico.util.EventoComparator;



@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes = {SpringContextConfigurationTest.class, WebConfig.class})
public class ApplicationTest {
	
	@Autowired
    private WebApplicationContext wac;
		
	private MockMvc mockMvc;
	
	@Autowired
	private CaixaEletronicoTest caixaTest;
		
	@Before
    public void setup() {
        final DefaultMockMvcBuilder builder = MockMvcBuilders.webAppContextSetup(this.wac);
        this.mockMvc = builder.build();
    }
	
	@Test
	public void extratoTest() throws Exception {
		final ContaCorrente cc = caixaTest.getConta("12345-0");
		cc.creditar(150);
		cc.creditar(50);
		cc.sacar(150);
		
		final List<EventoTransacional> eventosValidar = new ArrayList<>();
		eventosValidar.add(new ValorInicialDisponibilizadoEvento(0.0));
		eventosValidar.add(new ValorDepositadoEvento(150));
		eventosValidar.add(new ValorDepositadoEvento(50));
		eventosValidar.add(new ValorSacadoEvento(150));
		
		final ObjectMapper objectMapper = new ObjectMapper();
		final String jsonEsperado = objectMapper.writeValueAsString(new ExtratoWrapper(eventosValidar));

		final MvcResult result = mockMvc.perform(get("/12345-0/extrato")).andReturn();
		final String jsonAtual = result.getResponse().getContentAsString();
		
		JSONAssert.assertEquals(jsonEsperado, jsonAtual, new EventoComparator("data"));
		
		
	}
	
	
	@Test
	public void saldoTest() throws Exception {
		final ContaCorrente cc = caixaTest.getConta("12345-0");
		cc.creditar(121);
		cc.creditar(9);
		cc.creditar(20);
		
		final MvcResult result = mockMvc.perform(get("/12345-0/saldo")).andReturn();
		final String jsonHttp = result.getResponse().getContentAsString();
		
		final ObjectMapper objectMapper = new ObjectMapper();
		final String jsonSaldo = objectMapper.writeValueAsString(new ValorWrapper(150));
		
		JSONAssert.assertEquals(jsonHttp, jsonSaldo, true);
	}
	
	@Test
	public void depositoTest() throws Exception {
		final ContaCorrente cc = caixaTest.getConta("12345-0");
		final ObjectMapper objectMapper = new ObjectMapper();
		final String body = objectMapper.writeValueAsString(new ValorWrapper(1000));
		
		final MvcResult result = mockMvc.perform(post("/12345-0/deposito")
				.content(body)
				.contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
				.andReturn();

		assertEquals(1000, cc.saldo(), 0);
		assertEquals(HttpStatus.OK.value(), result.getResponse().getStatus());
	}
	
	@Test
	public void transferenciaTest() throws Exception {
		final ContaCorrente ccOrigem = caixaTest.getConta("12345-0");
		final ContaCorrente ccDestino = caixaTest.getConta("67890-0");
		ccOrigem.creditar(500);
		final ObjectMapper objectMapper = new ObjectMapper();
		final String body = objectMapper.writeValueAsString(new TransferenciaWrapper(ccDestino.getNumeroConta(), 233));
		
		final MvcResult result = mockMvc.perform(post("/12345-0/transferencia")
				.content(body)
				.contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
				.andReturn();

		assertEquals(233, ccDestino.saldo(), 0);
		assertEquals(267, ccOrigem.saldo(), 0);
		assertEquals(HttpStatus.OK.value(), result.getResponse().getStatus());
	}

}