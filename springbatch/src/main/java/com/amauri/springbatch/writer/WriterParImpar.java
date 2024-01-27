package com.amauri.springbatch.writer;

import org.springframework.batch.item.ItemWriter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class WriterParImpar {

	@Bean
	ItemWriter<String> imprimeWriter() {
		return itens -> itens.forEach(System.out::println);
	}
}
