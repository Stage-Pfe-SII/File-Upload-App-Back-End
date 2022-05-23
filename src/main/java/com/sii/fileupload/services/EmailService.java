package com.sii.fileupload.services;

import com.sii.fileupload.entities.Transfert;

public interface EmailService {
    void sendToSender(Transfert transfert);
    void sendToReceiver(Transfert transfert);
}
