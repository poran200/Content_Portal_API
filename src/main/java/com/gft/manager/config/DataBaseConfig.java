package com.gft.manager.config;

import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import dev.morphia.Datastore;
import dev.morphia.Morphia;
import eu.europeana.batch.config.MongoBatchConfigurer;
import eu.europeana.batch.entity.PackageMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.task.SimpleAsyncTaskExecutor;
import org.springframework.data.mongodb.core.MongoTemplate;

@Configuration
public class DataBaseConfig {
    @Value("${mongodb.url}")
    private String mongoUrl;

    @Value("${mongodb.name}")
    private String mongoDBName;

    @Bean
    public MongoClient mongo() {
        ConnectionString connectionString = new ConnectionString(mongoUrl);
        MongoClientSettings mongoClientSettings = MongoClientSettings.builder()
                .applyConnectionString(connectionString)
                .build();

        return MongoClients.create(mongoClientSettings);
    }

    @Bean
    public MongoTemplate mongoTemplate() {
        return new MongoTemplate(mongo(), mongoDBName);
    }
    public Datastore batchDatastore() {
        Datastore datastore = Morphia.createDatastore(mongo(), mongoDBName);

        // Required to create indices on database
        datastore.getMapper().mapPackage(PackageMapper.class.getPackageName());
        datastore.ensureIndexes();
        return datastore;
    }
    @Bean
    public MongoBatchConfigurer mongoBatchConfigurer() {
        return new MongoBatchConfigurer(batchDatastore(), new SimpleAsyncTaskExecutor());
    }
}
