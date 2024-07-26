package com.app.fourniture;

import org.springframework.boot.SpringApplication;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication(exclude={SecurityAutoConfiguration.class})
public class FournitureTransApp  {

    public static void main(String[] args) {

        SpringApplication.run(FournitureTransApp.class, args);
    }



}
