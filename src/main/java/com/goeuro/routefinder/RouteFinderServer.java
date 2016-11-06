package com.goeuro.routefinder;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan
public class RouteFinderServer {

    public static void main(String[] args) {
        SpringApplication.run(RouteFinderServer.class, args);
    }
}
