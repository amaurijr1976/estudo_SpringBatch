package br.com.primeiroprojetospringbatch;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;

@Configuration
public class BatchConfig {
	
		private final JobRepository jobRepository;
		private PlatformTransactionManager transactionManager;
		
		public BatchConfig(JobRepository jobRepository, PlatformTransactionManager transactionManager) {
			this.jobRepository = jobRepository;
			this.transactionManager = transactionManager;
		}
		
		@Bean
		public Job imprimeJob() {
			return new JobBuilder("imprimeJob",jobRepository)
							 .start(stepOlaMundo())
							 .build();
		}

		@Bean
		public Step stepOlaMundo() {
			return  new StepBuilder("OlaMundo",jobRepository)
					.tasklet(new Tasklet() {
						
						@Override
						public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
							System.out.println("Ola Mundo do batch");
							return RepeatStatus.FINISHED;
						}
					},transactionManager).build();
		}
		
	}