package com.sii.fileupload.services;

import com.sii.fileupload.entities.File;
import com.sii.fileupload.entities.Transfert;

import java.util.List;

public interface TransfertService {
    Transfert save(Transfert transfert);
    Transfert findByPath(String path);
    Transfert addFilesToTransfert(Transfert transfert, List<File> files);
    Transfert incrementDownloadTime(Transfert transfert);
    void deleteExpiredTransferts();
}
