package org.polytech.filmapi.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.persistence.autoconfigure.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EntityScan("org.polytech.filmapi")
@EnableJpaRepositories("org.polytech.filmapi")
public class App {

    static void main(String[] args) {
        SpringApplication.run(App.class, args);
    }
}
