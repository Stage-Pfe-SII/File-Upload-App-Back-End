package com.sii.fileupload.services;

import com.sii.fileupload.entities.Transfert;

public interface TransfertService {
    Transfert save(Transfert transfert);
    Transfert findByPath(String path);
}
