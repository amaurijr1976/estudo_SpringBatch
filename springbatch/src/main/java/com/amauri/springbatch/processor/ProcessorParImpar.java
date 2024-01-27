package com.amauri.springbatch.processor;

import org.springframework.batch.item.function.FunctionItemProcessor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ProcessorParImpar {

	@Bean
	FunctionItemProcessor<Integer,String> parOuImparProcessor() {
		return new FunctionItemProcessor<Integer,String>
				(item -> item % 2 == 0
				 		 ? String.format("Item %s é par", item):
					       String.format("Item %s é impar", item));
	}	
}
