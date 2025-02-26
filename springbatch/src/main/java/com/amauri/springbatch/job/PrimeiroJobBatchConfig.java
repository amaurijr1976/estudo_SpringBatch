package com.amauri.springbatch.job;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class PrimeiroJobBatchConfig {

	private JobRepository jobRepository;
	
	public PrimeiroJobBatchConfig(JobRepository jobRepository) {
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
