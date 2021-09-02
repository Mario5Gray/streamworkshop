package com.example.workshop.test;

import com.example.workshop.function.conflate.ConflateFunctions;
import com.example.workshop.service.impl.RepositoryStockService;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.stream.binder.test.TestChannelBinderConfiguration;
import org.springframework.cloud.stream.config.BindingServiceConfiguration;
import org.springframework.context.annotation.Import;
import org.springframework.data.mongodb.repository.config.EnableReactiveMongoRepositories;

@SpringBootApplication
@EnableReactiveMongoRepositories(basePackages = {"com.example.workshop.repository"})
@Import({ConflateFunctions.class,
        TestChannelBinderConfiguration.class,
        BindingServiceConfiguration.class})
public class TestServiceConfig {

}
