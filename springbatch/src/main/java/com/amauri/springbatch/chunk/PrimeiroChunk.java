package com.amauri.springbatch.chunk;

import java.util.Arrays;
import java.util.List;

import org.springframework.batch.core.Step;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.function.FunctionItemProcessor;
import org.springframework.batch.item.support.IteratorItemReader;
import org.springframework.stereotype.Component;
import org.springframework.transaction.PlatformTransactionManager;

@Component
public class PrimeiroChunk{

	private JobRepository jobRepository;
	private PlatformTransactionManager transactionManager;
	
	public Step imprimeParImpar() {
		return new StepBuilder("parOuImpar", jobRepository)
				.<Integer, String>chunk(1, transactionManager)
				.reader(contaAteDezReader())
				.processor(parOuImparProcessor())
				.writer(imprimeWriter())
				.build();
	}
	
	private IteratorItemReader<Integer> contaAteDezReader() {
		List<Integer> listaNumeros = Arrays.asList(1,2,3,4,5,6,7,8,9,10);
		return new IteratorItemReader<Integer>(listaNumeros.iterator());
	}

	private FunctionItemProcessor<Integer,String> parOuImparProcessor() {
		return new FunctionItemProcessor<Integer,String>
				(item -> item % 2 == 0
				 		 ? String.format("Item %s é par", item):
					       String.format("Item %s é impar", item));
	}


	private ItemWriter<String> imprimeWriter() {
		return itens -> itens.forEach(System.out::println);
	}
}
