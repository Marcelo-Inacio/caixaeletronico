package br.com.hyperclass.caixaeletronico.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
//import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
//import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.web.client.RestTemplate;

/**
 * A classe <code>SpringContextConfiguration</code> representa a configuracao do
 * contexto do Spring da aplicacao.
 *
 * 
 */
@Configuration
@ComponentScan(basePackages = {"br.com.hyperclass.caixaeletronico.restapi.serializer",""})
//@PropertySource({""})
@Import({DomainEventsBeans.class})
public class SpringContextConfiguration {
	
	@Bean
    public static PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer() {
        return new PropertySourcesPlaceholderConfigurer();
    }

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }


}
