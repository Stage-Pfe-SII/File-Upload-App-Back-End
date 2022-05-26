package com.sii.fileupload.controllers;

import com.sii.fileupload.entities.File;
import com.sii.fileupload.entities.Transfert;
import com.sii.fileupload.excpetions.TransfertNotFoundException;
import com.sii.fileupload.services.TransfertService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

@RestController
@RequiredArgsConstructor
public class DownloadController {

    private final TransfertService transfertService;

    @GetMapping("/download/{path}")
    public void downloadZipFile(@PathVariable String path, HttpServletResponse response)throws Exception{
        Transfert transfert = transfertService.findByPath(path);
        if(transfert == null){
            throw new TransfertNotFoundException("the transfert with the path "+path+" does not exist");
        }
        List<File> files = (List<File>) transfert.getFiles();
        ZipOutputStream zipOutputStream = new ZipOutputStream(response.getOutputStream());
        response.setContentType("application/zip");
        response.setStatus(HttpServletResponse.SC_OK);
        response.setHeader(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"file.zip\"");
        transfertService.incrementDownloadTime(transfert);
        files.forEach(file -> {
            ZipEntry entry = new ZipEntry(file.getName());
            entry.setSize(file.getSize());
            try {
                zipOutputStream.putNextEntry(entry);
                StreamUtils.copy(file.getContent(), zipOutputStream);
                zipOutputStream.closeEntry();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
        zipOutputStream.close();
    }
}
