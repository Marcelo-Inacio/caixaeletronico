package br.com.hyperclass.caixaeletronico.config;

import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
//import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.http.MediaType;
import org.springframework.web.accept.ContentNegotiationManager;
import org.springframework.web.accept.ContentNegotiationManagerFactoryBean;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.ContentNegotiationConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.view.ContentNegotiatingViewResolver;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

/**
 * A classe <code>WebConfig</code> contém as configurações do Spring referentes
 * à camada de apresentação.
 *
 * 
 */
@Configuration
@EnableWebMvc
@ComponentScan(basePackages = {"br.com.hyperclass.caixaeletronico.restapi"}) //aqui fica onde estão os controllers
//@PropertySource({"", ""})  //aqui fica onde estão localizados os .properties
public class WebConfig extends WebMvcConfigurerAdapter {
	
	@Override
    public void configureContentNegotiation(final ContentNegotiationConfigurer configurer) {
        configurer.defaultContentType(MediaType.APPLICATION_JSON);
        configurer.mediaTypes(getMediaTypes());
    }

    @Bean
    public static PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer() {
        return new PropertySourcesPlaceholderConfigurer();
    }

    @Bean
    public ViewResolver viewResolver(final ContentNegotiationManager manager) {
        final ContentNegotiatingViewResolver resolver = new ContentNegotiatingViewResolver();
        resolver.setContentNegotiationManager(manager);
        return resolver;
    }

    @Bean
    public ContentNegotiatingViewResolver contentViewResolver() throws Exception {
        final ContentNegotiationManagerFactoryBean contentNegotiationManager = new ContentNegotiationManagerFactoryBean();
        contentNegotiationManager.addMediaTypes(getMediaTypes());
        contentNegotiationManager.setDefaultContentType(MediaType.APPLICATION_JSON);

        final MappingJackson2JsonView defaultJsonView = new MappingJackson2JsonView();
        defaultJsonView.setExtractValueFromSingleKeyModel(true);

        final ContentNegotiatingViewResolver contentViewResolver = new ContentNegotiatingViewResolver();
        contentViewResolver.setContentNegotiationManager(contentNegotiationManager.getObject());
        //contentViewResolver.setDefaultViews(Arrays.<View> asList(defaultJsonView, new MappingJackson2XmlView()));

        return contentViewResolver;
    }

    private Map<String, MediaType> getMediaTypes() {
        final Map<String, MediaType> mediaTypes = new HashMap<>();
        mediaTypes.put("json", MediaType.APPLICATION_JSON);
        return mediaTypes;
    }

    @Bean
    public PageableHandlerMethodArgumentResolver pageableResolver() {
        final PageableHandlerMethodArgumentResolver resolver = new PageableHandlerMethodArgumentResolver();
        resolver.setFallbackPageable(new PageRequest(0, 12));
        return resolver;
    }

    @Override
    public void addArgumentResolvers(final List<HandlerMethodArgumentResolver> argumentResolvers) {
        argumentResolvers.add(pageableResolver());
        super.addArgumentResolvers(argumentResolvers);
    }

}
