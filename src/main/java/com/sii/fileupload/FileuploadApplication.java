package com.sii.fileupload;

import com.sii.fileupload.entities.File;
import com.sii.fileupload.servcices.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.FileInputStream;

@SpringBootApplication
public class FileuploadApplication implements CommandLineRunner {

    @Autowired
    private FileService fileService;

    public static void main(String[] args) {
        SpringApplication.run(FileuploadApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        java.io.File f = new java.io.File("C:\\SystemLogo.bmp");
        FileInputStream inputStream = new FileInputStream(f);
        byte[] data = inputStream.readAllBytes();
        File file = new File(null,f.getName(),f.getPath(),data.length,data,null);

        fileService.save(file);

    }
}
