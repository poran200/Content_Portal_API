package com.gft.manager.batch.config;

import com.gft.manager.batch.listeners.GiftCardInvoiceItemReaderListener;
import com.gft.manager.batch.listeners.JobCompletionListener;
import com.gft.manager.batch.processor.GiftCardInvoiceProcessor;
import com.gft.manager.batch.reader.GiftCardInvoiceItemReader;
import com.gft.manager.batch.validators.GiftCardJobParameterValidator;
import com.gft.manager.model.gft.GiftCardInvoice;
import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.ItemReadListener;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParametersValidator;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.JobScope;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.core.job.CompositeJobParametersValidator;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.extensions.excel.mapping.BeanWrapperRowMapper;
import org.springframework.batch.item.data.MongoItemWriter;
import org.springframework.batch.item.data.builder.MongoItemWriterBuilder;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.LineCallbackHandler;
import org.springframework.batch.item.file.builder.FlatFileItemReaderBuilder;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.task.SimpleAsyncTaskExecutor;
import org.springframework.core.task.TaskExecutor;
import org.springframework.data.mongodb.core.MongoTemplate;

import java.io.IOException;
import java.util.Collections;


@Configuration
@RequiredArgsConstructor
public class BatchConfiguration {

    public static final String GIFT_CARD_INVOICE = "giftCardInvoice";
    private final JobBuilderFactory jobBuilderFactory;
    private final StepBuilderFactory stepBuilderFactory;
    private final GiftCardInvoiceProcessor invoiceProcessor;


    @Bean
    public JobParametersValidator jobParametersValidator() {
        return new GiftCardJobParameterValidator();

    }

    @Bean
    public JobParametersValidator compositeJobParametersValidator() {
        CompositeJobParametersValidator bean = new CompositeJobParametersValidator();
        bean.setValidators(Collections.singletonList(jobParametersValidator()));
        return bean;
    }


    @Bean
    @StepScope
    public FlatFileItemReader<GiftCardInvoice> itemReader(@Value("#{jobParameters[excelPath]}") String pathToFile) {
        return new FlatFileItemReaderBuilder<GiftCardInvoice>()
                .name("invoice file item Reader")
                .resource(new FileSystemResource(pathToFile))
                .lineTokenizer(new DelimitedLineTokenizer())
                .fieldSetMapper(fieldSet -> GiftCardInvoice.builder()
                        .invoiceNo(fieldSet.readString(0))
                        .uniqueInvoiceNo(fieldSet.readString(1))
                        .orderDate(fieldSet.readString(2))
                        .orderTime(fieldSet.readString(3))
                        .customerId(fieldSet.readString(4))
                        .coustomerName(fieldSet.readString(5))
                        .contactNo(fieldSet.readString(6))
                        .coustomerAddress(fieldSet.readString(7))
                        .orderQuantity(fieldSet.readInt(8))
                        .orderPrice(fieldSet.readDouble(9))
                        .actualPrice(fieldSet.readDouble(10))
                        .from(fieldSet.readString(11))
                        .validityMonth(fieldSet.readInt(12))
                        .build())
                .linesToSkip(1)
                 .build();
    }

    @Bean
    @StepScope
    public MongoItemWriter<GiftCardInvoice> writer(MongoTemplate mongoTemplate) {
        return new MongoItemWriterBuilder<GiftCardInvoice>()
                .template(mongoTemplate).collection(GIFT_CARD_INVOICE)
                .build();

    }

    @Bean
    @JobScope
    public Step giftCardImportStep(MongoItemWriter<GiftCardInvoice> itemWriter) throws IOException {
        return stepBuilderFactory.get("giftCardImportStep")
                .<GiftCardInvoice, GiftCardInvoice>chunk(1000)
                .reader(itemReader(null))
                .listener(new GiftCardInvoiceItemReaderListener())
                .processor(invoiceProcessor)
                .writer(itemWriter)
                .taskExecutor(taskExecutor())
                .build();

    }

    @Bean
    public Job giftCardImportJob(JobCompletionListener listener, Step giftCardImportStepStep) {
        return jobBuilderFactory.get("giftCardImportJob")
                .incrementer(new RunIdIncrementer())
                .listener(listener)
                .flow(giftCardImportStepStep)
                .end()
                .validator(compositeJobParametersValidator())
                .build();

    }

    @Bean
    public BeanWrapperRowMapper<GiftCardInvoice> getRowMapper() {
        BeanWrapperRowMapper<GiftCardInvoice> mapper = new BeanWrapperRowMapper<>();
        mapper.setTargetType(GiftCardInvoice.class);
        return mapper;
    }

    @Bean
    public TaskExecutor taskExecutor() {
        SimpleAsyncTaskExecutor asyncTaskExecutor = new SimpleAsyncTaskExecutor("spring_batch");
        asyncTaskExecutor.setConcurrencyLimit(Runtime.getRuntime().availableProcessors() -1);
        return asyncTaskExecutor;
    }
}
