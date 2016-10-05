package br.com.hyperclass.caixaeletronico.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@ComponentScan("br.com.hyperclass.caixaeletronico.util")
@Import(CaixaEletronicoTestBeans.class)
public class SpringContextConfigurationTest {
}