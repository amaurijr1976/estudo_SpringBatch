package com.amauri.springbatch.job;

import java.util.Arrays;
import java.util.List;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.function.FunctionItemProcessor;
import org.springframework.batch.item.support.IteratorItemReader;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;

@Configuration
public class PrimeiroJobBatchConfig {

	private JobRepository jobRepository;
	
	public PrimeiroJobBatchConfig(JobRepository jobRepository, PlatformTransactionManager transactionManager) {
		this.jobRepository = jobRepository;
	}
	
	@Bean
	public Job imprimeJob(Step primeiroStep) {
		return new JobBuilder("imprimeJob",jobRepository)
						 .start(primeiroStep)
						 .incrementer(new RunIdIncrementer())
						 .build();
	}
}
