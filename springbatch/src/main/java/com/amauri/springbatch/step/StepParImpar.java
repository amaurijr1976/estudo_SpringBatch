package com.amauri.springbatch.step;

import org.springframework.batch.core.Step;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;

@Configuration
public class StepParImpar {
	
	private JobRepository jobRepository;
	private PlatformTransactionManager transactionManager;

	public StepParImpar(JobRepository jobRepository,PlatformTransactionManager transactionManager) {
		this.jobRepository = jobRepository;
		this.transactionManager = transactionManager;
	}
	
	  @Bean 
		public Step imprimeParImpar(ItemReader<Integer> reader,
									ItemProcessor<Integer,String> processor
									,ItemWriter<String> writer) {
			return new StepBuilder("parOuImpar", jobRepository)
					.<Integer, String>chunk(1, transactionManager)
					.reader(reader)
					.processor(processor)
					.writer(writer)
					.build();
		}
}
