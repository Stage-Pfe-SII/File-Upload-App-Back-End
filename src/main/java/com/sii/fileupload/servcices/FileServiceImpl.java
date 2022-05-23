package com.sii.fileupload.servcices;

import com.sii.fileupload.entities.File;
import com.sii.fileupload.entities.Transfert;
import com.sii.fileupload.repositories.FileRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FileServiceImpl implements FileService{

    public final FileRepository fileRepository;

    public FileServiceImpl(FileRepository fileRepository) {
        this.fileRepository = fileRepository;
    }

    @Override
    public File save(File file) {
        return fileRepository.save(file);
    }

    @Override
    public List<File> findByTransfert(Transfert transfert) {
        return fileRepository.findByTransfert(transfert);
    }

    @Override
    public List<File> findByPath(String path) {
        return fileRepository.findByPath(path);
    }
}
