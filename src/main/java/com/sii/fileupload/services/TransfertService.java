package com.sii.fileupload.services;

import com.sii.fileupload.entities.Transfert;
import com.sii.fileupload.repositories.TransfertRepository;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.util.Date;
import java.util.UUID;

@Service
public class TransfertService {
    private TransfertRepository transfertRepository;

    public TransfertService(TransfertRepository transfertRepository) {
        this.transfertRepository = transfertRepository;
    }

    public Transfert save(Transfert transfert){
        String path = UUID.randomUUID().toString();
        Duration duration = Duration.ofDays(7);
        Date expirationDate = new Date(System.currentTimeMillis()+duration.toMillis());

        transfert.setPath(path);
        transfert.setExpirationDate(expirationDate);
        return transfertRepository.save(transfert);
    }

    public Transfert findByPath(String path){
        return transfertRepository.findByPath(path);
    }
}
