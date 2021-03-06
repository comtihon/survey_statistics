package com.surveyor.statistics;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Executor;

@EnableAsync
@SpringBootApplication
public class StatisticsApplication {

	public static void main(String[] args) {
		SpringApplication.run(StatisticsApplication.class, args);
	}

	@Bean
	public Executor asyncExecutor() {
		ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
		int cores = Runtime.getRuntime().availableProcessors();
		executor.setCorePoolSize(2);
		executor.setMaxPoolSize(cores * 2);
		executor.setQueueCapacity(500);
		executor.setThreadNamePrefix("Grabber-");
		executor.initialize();
		return executor;
	}
}
