package com.junior.estudoBatch.step;

import org.springframework.batch.core.Step;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;

@Configuration
public class StepOlaMundo {

    @Bean(name="imprimeOlaMundo")
    public Step imprimeOlaMundo(JobRepository jobRepository, PlatformTransactionManager transactionManager){
        return new StepBuilder("OlaMundo",jobRepository)
                .tasklet(getOlaMundao(null),transactionManager)
                .build();
    }

    @Bean
    @StepScope
    public Tasklet getOlaMundao(@Value("#{jobParameters['nome']}")String nome){
        return (StepContribution contribution, ChunkContext chunkContext) -> {
            System.out.println(String.format("Ola %s!",nome));
            return RepeatStatus.FINISHED;
        };
    }

}
