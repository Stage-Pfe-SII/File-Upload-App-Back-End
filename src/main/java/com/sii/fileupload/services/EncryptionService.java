package com.sii.fileupload.services;


import com.sii.fileupload.entities.File;

public interface EncryptionService {
    File encrypt(File file) throws Exception;
    File decrypt(File file) throws Exception;
}
