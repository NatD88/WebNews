package ru.deviatkina.webnews;

import org.apache.commons.io.FilenameUtils;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import org.springframework.context.annotation.Bean;
import org.springframework.web.filter.HiddenHttpMethodFilter;
import ru.deviatkina.webnews.storage.StorageService;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;


@SpringBootApplication

public class WebNewsApplication {

    public static void main(String[] args) {
        SpringApplication.run(WebNewsApplication.class, args);

    }

/*
    @Bean
    CommandLineRunner init(StorageService storageService) {
        return (args) -> {
            storageService.deleteAll();
            storageService.init();
        };
    }
*/
}
