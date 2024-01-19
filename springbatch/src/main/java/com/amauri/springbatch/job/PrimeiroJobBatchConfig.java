package com.amauri.springbatch.job;

import java.util.Arrays;
import java.util.List;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
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
	private PlatformTransactionManager transactionManager;
	
	public PrimeiroJobBatchConfig(JobRepository jobRepository, PlatformTransactionManager transactionManager) {
		this.jobRepository = jobRepository;
		this.transactionManager = transactionManager;
	}
	
	@Bean
	public Job imprimeJob(Step primeiroStep) {
		return new JobBuilder("imprimeJob",jobRepository)
						 .start(primeiroStep)
						 .incrementer(new RunIdIncrementer())
						 .build();
	}

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

	/*
	 * @Bean
	 * 
	 * @StepScope public Tasklet olaMundo(@Value("#{jobParameters['nome']}") String
	 * parametro) { return new Tasklet() {
	 * 
	 * @Override public RepeatStatus execute(StepContribution contribution,
	 * ChunkContext chunkContext) throws Exception {
	 * System.out.println(String.format("Ola %s!", parametro)); return
	 * RepeatStatus.FINISHED; } }; }
	 */
	
}
