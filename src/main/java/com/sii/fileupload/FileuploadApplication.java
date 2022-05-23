package com.sii.fileupload;

import com.sii.fileupload.services.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class FileuploadApplication{

    @Autowired
    private FileService fileService;

    public static void main(String[] args) {
        SpringApplication.run(FileuploadApplication.class, args);
    }

}
