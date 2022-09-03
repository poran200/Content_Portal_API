package com.gft.manager.batch.config;

import com.gft.manager.batch.listeners.GiftCardInvoiceWriterListener;
import com.gft.manager.batch.listeners.JobCompletionListener;
import com.gft.manager.batch.reader.GiftCardInvoiceItemReader;
import com.gft.manager.batch.validators.GiftCardJobParameterValidator;
import com.gft.manager.model.gft.GiftCardInvoice;
import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParametersValidator;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.job.CompositeJobParametersValidator;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.data.MongoItemWriter;
import org.springframework.batch.item.data.builder.MongoItemWriterBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.MongoTemplate;

import java.util.Collections;


@Configuration
@RequiredArgsConstructor
public class BatchConfiguration {

    public static final String GIFT_CARD_INVOICE = "giftCardInvoice";
    private final JobBuilderFactory jobBuilderFactory;
    private final StepBuilderFactory stepBuilderFactory;


    @Bean
    public JobParametersValidator jobParametersValidator(){
        return  new GiftCardJobParameterValidator();

    }

    @Bean
    public JobParametersValidator compositeJobParametersValidator(){
        CompositeJobParametersValidator bean = new CompositeJobParametersValidator();
        bean.setValidators(Collections.singletonList(jobParametersValidator()));
        return bean;
    }
    @Bean
    public ItemReader<GiftCardInvoice> itemReader(){
        return new GiftCardInvoiceItemReader();
    }

    @Bean
    public MongoItemWriter<GiftCardInvoice> writer(MongoTemplate mongoTemplate){
        return new MongoItemWriterBuilder<GiftCardInvoice>()
                .template(mongoTemplate).collection(GIFT_CARD_INVOICE)
                .build();

    }

    @Bean
    public Step giftCardImportStep(GiftCardInvoiceWriterListener writerListener,MongoItemWriter<GiftCardInvoice> itemWriter){
        return stepBuilderFactory.get("giftCardImportStep")
                .<GiftCardInvoice,GiftCardInvoice>chunk(1000)
                .reader(itemReader())
                .writer(itemWriter)
                .listener(writerListener)
                .build();

    }
    @Bean
    public Job giftCardImportJob(JobCompletionListener listener,Step giftCardImportStepStep){
        return jobBuilderFactory.get("giftCardImportJob")
                .incrementer(new RunIdIncrementer())
                .listener(listener)
                .flow(giftCardImportStepStep)
                .end()
                .validator(compositeJobParametersValidator())
                .build();

    }
}
