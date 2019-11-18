package com.ioproj.niekopce;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.PropertySource;

@PropertySource("classpath:config.properties")
@SpringBootApplication
public class NiekopceApplication {

    public static void main(String[] args) {
        //SpringApplication.run(NiekopceApplication.class, args);
        ConfigurableApplicationContext ctx = new SpringApplicationBuilder(NiekopceApplication.class).headless(false).run(args);
    }

}
