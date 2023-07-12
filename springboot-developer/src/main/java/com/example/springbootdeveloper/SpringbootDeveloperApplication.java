package com.example.springbootdeveloper;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import static org.springframework.boot.SpringApplication.*;
@EnableJpaAuditing
@SpringBootApplication
public class SpringbootDeveloperApplication {

    public static void main(String[] args) {
        run(SpringbootDeveloperApplication.class, args);
    }

}
