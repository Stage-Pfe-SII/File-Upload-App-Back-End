package com.sii.fileupload.mapper;

import com.sii.fileupload.entities.File;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MultipartFileToFileMapper {

    public static File multipartFileToFile(MultipartFile multipartFile){
        File file = new File();
        try{
            file.setName(multipartFile.getOriginalFilename());
            file.setContent(multipartFile.getBytes());
            file.setSize(multipartFile.getBytes().length);
        }catch(IOException e){
            e.getStackTrace();
        }
        return file;
    }

    public static List<File> multipartFileListToFileList(List<MultipartFile> multipartFiles){
        List<File> files = new ArrayList<>();
        multipartFiles.forEach(multipartFile -> {
            files.add(multipartFileToFile(multipartFile));
        });
        return files;
    }
}
