package com.sii.fileupload;

import com.sii.fileupload.entities.File;
import com.sii.fileupload.entities.Transfert;
import com.sii.fileupload.services.TransfertService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.ArrayList;
import java.util.Arrays;

@SpringBootApplication
public class FileuploadApplication {

    public static void main(String[] args) {
        SpringApplication.run(FileuploadApplication.class, args);
    }

    @Bean
    public CommandLineRunner start(TransfertService transfertService){
        return args -> {
            Transfert transfert = testSaveTransfert(transfertService);
            testFetchTransfertByPath(transfertService, transfert.getPath());
        };
    }

    private Transfert testSaveTransfert(TransfertService transfertService){
        Transfert transfert = new Transfert();
        transfert.setSender("yassernadir761@gmail.com");
        transfert.setReceiver("yassernadir333@gmail.com");
        transfert.setTitle("sending from sender to receiver something");
        transfert.setMessage("lorem epsom");
        transfert.getFiles().addAll(
                Arrays.asList(
                        new File(null, "file1.txt", transfert.getPath(), 123, new byte[]{}, transfert),
                        new File(null, "file2.txt", transfert.getPath(), 1234, new byte[]{}, transfert)
                )
        );
        return transfertService.save(transfert);
    }

    private void testFetchTransfertByPath(TransfertService transfertService, String path){
        System.out.println(transfertService.findByPath(path));
    }
}
