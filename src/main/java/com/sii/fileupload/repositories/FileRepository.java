package com.sii.fileupload.repositories;

import com.sii.fileupload.entities.File;
import com.sii.fileupload.entities.Transfert;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FileRepository extends JpaRepository<File,Long> {
    List<File> findByTransfert(Transfert transfert);
    List<File> findByPath(String path);
}
