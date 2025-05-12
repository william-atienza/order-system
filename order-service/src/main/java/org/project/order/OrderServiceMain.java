package org.project.order;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

@SpringBootApplication
@EnableKafka
@EnableAsync
public class OrderServiceMain {
    public static void main(String[] args) {

        SpringApplication.run(OrderServiceMain.class);
    }

    @Bean(name = "customTPTaskExecutor")
	public ThreadPoolTaskExecutor threadPoolTaskExecutor(){
		ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
		executor.setCorePoolSize(6);
		executor.setMaxPoolSize(10);
		executor.setQueueCapacity(100);
		executor.setThreadNamePrefix("customAsyncThread-");
		executor.initialize();
		return executor;
	}
}