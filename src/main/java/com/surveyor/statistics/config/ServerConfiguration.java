package com.surveyor.statistics.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ServerConfiguration {

    @Value("${spring.data.mongodb.collection}")
    private String collection;

    public String getCollection() {
        return collection;
    }
}
