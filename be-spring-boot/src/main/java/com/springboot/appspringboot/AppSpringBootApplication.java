package com.springboot.appspringboot;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@RequiredArgsConstructor
@EnableJpaAuditing
public class AppSpringBootApplication {
    public static void main(String[] args) {
        SpringApplication.run(AppSpringBootApplication.class, args);
    }

}
