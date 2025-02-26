package com.junior.estudoBatch.job;

import com.junior.estudoBatch.step.ImprimeParImpar;
import com.junior.estudoBatch.step.StepOlaMundo;
import jdk.jfr.Percentage;
import org.springframework.batch.core.*;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.function.FunctionItemProcessor;
import org.springframework.batch.item.support.IteratorItemReader;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.transaction.PlatformTransactionManager;

import java.util.Arrays;
import java.util.List;

@Configuration
public class BatchConfig {

    @Qualifier("imprimeOlaMundo")
    @Autowired
    private Step imprimeOlaMundo;

    @Qualifier("imprimeParOuImpar")
    @Autowired
    private Step imprimeParOuImpar;

    @Bean
    public Job jobOlaMundo(JobRepository jobRepository, Step step
                            , PlatformTransactionManager transactionManager){
        return new JobBuilder("OlaBatch", jobRepository)
                .start(imprimeOlaMundo)
                //.start(step)
                .incrementer(new RunIdIncrementer())
                .next(imprimeParOuImpar)
                .build();
    }
}