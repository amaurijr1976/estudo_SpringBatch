package com.amauri.springbatch.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.io.FileSystemResource;

@Configuration
public class PropsConfig {

	@Bean
	public PropertySourcesPlaceholderConfigurer config() {
		PropertySourcesPlaceholderConfigurer props = new PropertySourcesPlaceholderConfigurer();
		props.setLocation(new FileSystemResource(getClass().getClassLoader().getResource("profiles/application-ext.properties").getFile()));
		return props;
	}
	
}
