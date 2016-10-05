package br.com.hyperclass.caixaeletronico.test;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
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
import br.com.hyperclass.caixaeletronico.restapi.wrapper.ExtratoWrapper;
import br.com.hyperclass.caixaeletronico.restapi.wrapper.ValorWrapper;


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
	public void extratoJsonTest() throws Exception {
		final ContaCorrente cc = caixaTest.getConta("12345-0");
		cc.creditar(150);
		cc.creditar(50);
		cc.sacar(150);
		
		ObjectMapper objectMapper = new ObjectMapper();
		String json = objectMapper.writeValueAsString(new ExtratoWrapper(cc.extrato()));
		
		final MvcResult result = mockMvc.perform(get("/12345-0/extrato")).andReturn();
		final String jsonHttp = result.getResponse().getContentAsString();
		
		JSONAssert.assertEquals(jsonHttp, json, true);
	}
	
	
	@Test
	public void saldoJsonTest() throws Exception {
		final ContaCorrente cc = caixaTest.getConta("12345-0");
		cc.creditar(121);
		cc.creditar(9);
		cc.creditar(20);
		
		final MvcResult result = mockMvc.perform(get("/12345-0/saldo")).andReturn();
		final String jsonHttp = result.getResponse().getContentAsString();
		
		//cc.sacar(22);
		ObjectMapper objectMapper = new ObjectMapper();
		String jsonSaldo = objectMapper.writeValueAsString(new ValorWrapper(cc.saldo()));
		
		
		JSONAssert.assertEquals(jsonHttp, jsonSaldo, true);
	}

}