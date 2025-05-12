package org.project.order.config;

import org.project.order.repository.AccountRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class LoadDatabase {
    private static final Logger log = LoggerFactory.getLogger(LoadDatabase.class);

    @Bean
    CommandLineRunner initDb(AccountRepository accountRepository) {
        return args -> {
//            log.info("Preloading {}", accountRepository.save(new Account()));
//            log.info("Preloading {}", accountRepository.save(new Account()));
//            log.info("Preloading {}", accountRepository.save(new Account()));
//            log.info("Preloading {}", productRepository.save(new Product("Funiture", 100, 120.00)));
//            log.info("Preloading {}", productRepository.save(new Product("Gadget", 50, 10.00)));
        };
    }
}
