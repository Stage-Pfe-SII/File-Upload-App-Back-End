package com.sii.fileupload.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sii.fileupload.Dto.TransfertDto;
import com.sii.fileupload.entities.File;
import com.sii.fileupload.entities.Transfert;
import com.sii.fileupload.mapper.TransfertMapper;
import com.sii.fileupload.services.EmailService;
import com.sii.fileupload.services.EncryptionService;
import com.sii.fileupload.services.FileService;
import com.sii.fileupload.services.TransfertService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

import static com.sii.fileupload.mapper.MultipartFileToFileMapper.multipartFileListToFileList;

@RestController
@RequiredArgsConstructor
@CrossOrigin("*")
public class UploadController {

    private final TransfertService transfertService;
    private final FileService fileService;
    private final EmailService emailService;
    private final TransfertMapper transfertMapper;
    private final EncryptionService encryptionService;


    @PostMapping("/upload")
    public void upload(
            @RequestParam("files") List<MultipartFile> multipartFiles,
            @RequestParam("transfertJSON") String transfertJSON
            ) {
        try {
            TransfertDto trans = new ObjectMapper().readValue(transfertJSON, TransfertDto.class);
            Transfert transfert = transfertMapper.transfertDtoToTransfert(trans);
            transfertService.save(transfert);
            List<File> files = multipartFileListToFileList(multipartFiles);
            files.forEach(file -> {
                try {
                    encryptionService.encrypt(file);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            });
            transfertService.addFilesToTransfert(transfert,files);
            emailService.sendToSender(transfert);
            emailService.sendToReceiver(transfert);
            transfertService.deleteExpiredTransferts();
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }

}
