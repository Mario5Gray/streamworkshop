package com.example.workshop.test;

import com.example.workshop.function.conflate.ConflateFunctions;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.stream.binder.test.TestChannelBinderConfiguration;
import org.springframework.cloud.stream.config.BindingServiceConfiguration;
import org.springframework.context.annotation.Import;

@SpringBootApplication
@Import({ConflateFunctions.class,
        TestChannelBinderConfiguration.class,
        BindingServiceConfiguration.class})
public class TestServiceConfig {

}