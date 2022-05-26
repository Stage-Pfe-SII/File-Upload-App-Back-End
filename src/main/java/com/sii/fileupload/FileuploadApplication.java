package com.sii.fileupload;

import com.sii.fileupload.entities.Transfert;
import com.sii.fileupload.services.EmailService;
import com.sii.fileupload.services.FileService;
import com.sii.fileupload.services.TransfertService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class FileuploadApplication{

    @Autowired
    private FileService fileService;

    public static void main(String[] args) {
        SpringApplication.run(FileuploadApplication.class, args);
    }



}
