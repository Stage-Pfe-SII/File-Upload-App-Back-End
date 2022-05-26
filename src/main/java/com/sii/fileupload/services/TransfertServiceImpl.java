package com.sii.fileupload.services;

import com.sii.fileupload.entities.File;
import com.sii.fileupload.entities.Transfert;
import com.sii.fileupload.repositories.TransfertRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.Duration;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class TransfertServiceImpl implements TransfertService {
    private final TransfertRepository transfertRepository;

    @Override
    public Transfert save(Transfert transfert){
        String path = UUID.randomUUID().toString();
        Duration duration = Duration.ofDays(7);
        Date expirationDate = new Date(System.currentTimeMillis()+duration.toMillis());
        transfert.setPath(path);
        transfert.setExpirationDate(expirationDate);
        transfert.setDownloadTimes(0);
        return transfertRepository.save(transfert);
    }

    @Override
    public List<Transfert> getAllTransferts() {
        return transfertRepository.findAll();
    }

    @Override
    public Transfert findByPath(String path){
        return transfertRepository.findByPath(path);
    }

    @Override
    @Transactional
    public Transfert addFilesToTransfert(Transfert transfert, List<File> files) {
        files.forEach(file -> {
            file.setPath(transfert.getPath());
            file.setTransfert(transfert);
            transfert.getFiles().add(file);
        });
        return transfert;
    }

    @Override
    @Transactional
    public Transfert incrementDownloadTime(Transfert transfert) {
        transfert.setDownloadTimes(transfert.getDownloadTimes() + 1);
        return transfert;
    }

    @Override
    @Transactional
    public void deleteExpiredTransferts() {
        List<Transfert> transferts = transfertRepository.findAll();
        transferts.forEach(transfert -> {
            if(transfert.getExpirationDate().before(new Date())){
                transfertRepository.delete(transfert);
            }
        }
        );
    }


}
