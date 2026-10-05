package org.polytech.filmapi.app;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@ComponentScan("org.polytech.filmapi")
@PropertySource( "classpath:application.yaml")
public class AppConfig {

}
