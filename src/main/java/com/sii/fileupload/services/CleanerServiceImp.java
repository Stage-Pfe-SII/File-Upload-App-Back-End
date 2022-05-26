package com.sii.fileupload.services;

import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CleanerServiceImp {

    private final TransfertService transfertService;

    @Scheduled(initialDelay = 0 ,fixedDelay = 1000 * 60 * 60 * 24 * 7)
    public void cleanExpiredFiles(){
        transfertService.deleteExpiredTransferts();
    }
}
