package com.amauri.springbatch.step;

import org.springframework.batch.core.Step;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;

@Configuration
public class PrimeiroStepConfig {
	@Autowired
	private JobRepository jobRepository;
	
	@Autowired
	private PlatformTransactionManager transactionManager;
	
    @Bean
    Step primeiroStepTasklet(Tasklet tasklet) { 
		  return new StepBuilder("OlaMundo",jobRepository)
				  	  .tasklet(tasklet,transactionManager).build();
      }
	  
	  
	 
	
}
