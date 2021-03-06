package br.com.hyperclass.caixaeletronico.config;


import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * A classe <code>SpringContextConfiguration</code> representa a configuracao do
 * contexto do Spring da aplicacao.
 *
 * 
 */
@Configuration
@ComponentScan("br.com.hyperclass.caixaeletronico.util")
@Import(CaixaEletronicoBeans.class)
public class SpringContextConfiguration {
}