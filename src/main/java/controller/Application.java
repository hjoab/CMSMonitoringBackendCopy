package controller;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;

import java.util.Date;

@SpringBootApplication
@ComponentScan(basePackages = "controller")
@EnableAutoConfiguration
@PropertySources({
        @PropertySource("classpath:application.properties"),
        @PropertySource("classpath:auth0.properties")
})
public class Application {
    public static void main(String[] args){

        System.out.println("Latest changes: " + System.currentTimeMillis());
        SpringApplication.run(Application.class,args);
    }
}
