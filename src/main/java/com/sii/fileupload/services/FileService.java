package com.sii.fileupload.services;


import com.sii.fileupload.entities.File;
import com.sii.fileupload.entities.Transfert;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface FileService {
    File save(File file);
    List<File> findByTransfert(Transfert transfert);
    List<File> findByPath(String path);
}
