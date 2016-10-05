package br.com.hyperclass.caixaeletronico.util;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

@Component
public class BeanRetriever implements ApplicationContextAware {

	private static ApplicationContext context;
	
	@Override
	public void setApplicationContext(final ApplicationContext ctx) throws BeansException {
		context = ctx;
	}

	public static <T> T getBean(final String name, final Class<T> clazz) {
		return context.getBean(name, clazz);
	}
}