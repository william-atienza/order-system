package org.project;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.kafka.annotation.EnableKafka;

@SpringBootApplication
@EnableKafka
public class ShippingServiceMain {
    public static void main(String[] args) {
        SpringApplication.run(ShippingServiceMain.class);
    }
}