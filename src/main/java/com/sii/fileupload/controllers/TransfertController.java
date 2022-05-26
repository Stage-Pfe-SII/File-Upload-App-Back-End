package com.sii.fileupload.controllers;

import com.sii.fileupload.entities.Transfert;
import com.sii.fileupload.services.TransfertService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class TransfertController {

    private final TransfertService transfertService;

    @GetMapping("/tranferts")
    List<Transfert> getTransferts(){
        return transfertService.getAllTransferts();
    }

}
