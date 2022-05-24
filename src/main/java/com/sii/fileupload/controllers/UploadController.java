package com.sii.fileupload.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sii.fileupload.Dto.TransfertDto;
import com.sii.fileupload.entities.File;
import com.sii.fileupload.entities.Transfert;
import com.sii.fileupload.mapper.MultipartFileToFileMapper;
import com.sii.fileupload.mapper.TransfertMapper;
import com.sii.fileupload.services.EmailService;
import com.sii.fileupload.services.FileService;
import com.sii.fileupload.services.TransfertService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


@RestController
@RequiredArgsConstructor
public class UploadController {

    private final TransfertService transfertService;
    private final FileService fileService;
    private final EmailService emailService;
    private final TransfertMapper transfertMapper;

    @PostMapping("/upload")
    public void upload(
            @RequestParam("files") List<MultipartFile> multipartFiles,
            @RequestParam("transfertDto") String transfertDto
            ) {
        try {
            TransfertDto trans = new ObjectMapper().readValue(transfertDto, TransfertDto.class);
            Transfert transfert = transfertMapper.transfertDtoToTransfert(trans);
            transfertService.save(transfert);
            List<File> files = MultipartFileToFileMapper.multipartFileListToFileList(multipartFiles);
            transfertService.addFilesToTransfert(transfert,files);

            emailService.sendToSender(transfert);
            emailService.sendToReceiver(transfert);

        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }

}
