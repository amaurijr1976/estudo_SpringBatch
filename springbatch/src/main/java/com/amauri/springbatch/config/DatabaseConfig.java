package com.amauri.springbatch.config;

import javax.sql.DataSource;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@Configuration
public class DatabaseConfig {

	@Primary
	@Bean
	@ConfigurationProperties("spring.datasource")
	public DataSource springDataBase() {
		return DataSourceBuilder.create().build();
	}
	
	@Bean
	@ConfigurationProperties("app.datasource")
	public DataSource appDataBase() {
		return DataSourceBuilder.create().build();
	}
	
}
