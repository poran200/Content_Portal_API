package com.gft.manager;

import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@EnableBatchProcessing
@EnableMongoRepositories(value = "com.gft.manager.repository")
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})

public class UnrivielApiAppApplication {

    public static void main(String[] args) {
        SpringApplication.run(UnrivielApiAppApplication.class, args);
    }


}
