package com.surveyor.statistics.service;

import com.surveyor.statistics.config.ServerConfiguration;
import com.surveyor.statistics.data.dto.ResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.concurrent.CompletableFuture;

@Service
public class StatisticsService {

    @Autowired
    private MongoTemplate mongoTemplate;

    @Autowired
    private ServerConfiguration configuration;

    @Async
    public CompletableFuture<ResponseDTO<?>> grab(String id) {
        Query query = new Query(Criteria.where("question_id").is(id));
        Map res = mongoTemplate.findOne(query, Map.class, configuration.getCollection());
        if (res == null) {
            ResponseDTO<String> resp = new ResponseDTO<>(false, "no data for id");
            return CompletableFuture.completedFuture(resp);
        }
        ResponseDTO<Map> resp = new ResponseDTO<>(res);
        return CompletableFuture.completedFuture(resp);
    }
}
