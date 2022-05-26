package com.sii.fileupload;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class FileuploadApplication{

    public static void main(String[] args) {
        SpringApplication.run(FileuploadApplication.class, args);
    }

}
