package com.amauri.springbatch.tasklet;

import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.stereotype.Component;

@Component
public class PrimeiroTasklet implements Tasklet {
	
	public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
		 System.out.println(String.format("Ola Mundo !!!")); 
		 return RepeatStatus.FINISHED;
	}

}
