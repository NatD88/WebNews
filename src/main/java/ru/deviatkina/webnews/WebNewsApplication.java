package ru.deviatkina.webnews;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.filter.HiddenHttpMethodFilter;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;


@SpringBootApplication
public class WebNewsApplication {

    public static void main(String[] args) {
        SpringApplication.run(WebNewsApplication.class, args);

    }

}
