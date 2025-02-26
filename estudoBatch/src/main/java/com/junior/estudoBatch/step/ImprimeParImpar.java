package com.junior.estudoBatch.step;

import org.springframework.batch.core.Step;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.function.FunctionItemProcessor;
import org.springframework.batch.item.support.IteratorItemReader;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;

import java.util.Arrays;
import java.util.List;

@Configuration
public class ImprimeParImpar {


    @Bean(name="imprimeParOuImpar")
    public Step imprimeParOuImpar(JobRepository jobRepository, PlatformTransactionManager plataformTransaciton) {
        return new StepBuilder("ImprimeParImpar",jobRepository)
                .<Integer,String>chunk(1,plataformTransaciton)
                .reader(leListaNumeros())
                .processor(parOuImparProcessor())
                .writer(imprimeNumero())
                .build();
    }

    private FunctionItemProcessor<Integer, String> parOuImparProcessor() {
        return new FunctionItemProcessor<Integer,String>
                (item -> item % 2 == 0 ? String.format("Esse numero: %s é Par",item): String.format("Esse numero: %s é Impar",item) );
    }

    private IteratorItemReader<Integer> leListaNumeros() {
        List<Integer> listaNumeros = Arrays.asList(1,2,3,4,5,6,7,8,9,10);
        return new IteratorItemReader<Integer>(listaNumeros.iterator());
    }


    private ItemWriter<String> imprimeNumero() {
        return itens -> itens.forEach(System.out::println);
    }


}
