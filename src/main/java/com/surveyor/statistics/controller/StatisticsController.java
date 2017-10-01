package com.surveyor.statistics.controller;

import com.surveyor.statistics.data.dto.ResponseDTO;
import com.surveyor.statistics.service.StatisticsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.nio.charset.Charset;
import java.util.concurrent.CompletableFuture;

@RestController
public class StatisticsController {

    private static final MediaType CONTENT_TYPE = new MediaType(MediaType.APPLICATION_JSON.getType(),
            MediaType.APPLICATION_JSON.getSubtype(),
            Charset.forName("utf8"));

    @Autowired
    private StatisticsService statisticsService;

    @RequestMapping(path = "/question/{id}", method = RequestMethod.GET)
    public CompletableFuture<ResponseEntity<?>> get(@PathVariable("id") String id) {
        CompletableFuture<ResponseDTO<?>> got = statisticsService.grab(id);
        return got.thenApply(this::returnResult);
    }

    private ResponseEntity<ResponseDTO<?>> returnResult(ResponseDTO<?> result) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(CONTENT_TYPE);
        if (result.isResult())
            return new ResponseEntity<>(result, headers, HttpStatus.OK);
        return new ResponseEntity<>(result, headers, HttpStatus.BAD_REQUEST);
    }
}
