package com.sii.fileupload.services;

import com.sii.fileupload.entities.Transfert;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

@Service
public class EmailServiceImpl implements EmailService {
    private JavaMailSender javaMailSender;

    @Value("${spring.mail.username}")
    private String sender;

    public EmailServiceImpl(JavaMailSender javaMailSender) {
        this.javaMailSender = javaMailSender;
    }

    @Override
    public void sendToSender(Transfert transfert) {
        String body = "your are the sender";
        String receiver = "yassernadir761@gmail.com";
        String subject = "testing sending mails";
        sendMail(body, receiver, subject);
    }

    @Override
    public void sendToReceiver(Transfert transfert) {
        String body = "your are the receiver";
        String receiver = "yasser.nadir@sii-maroc.com";
        String subject = "testing sending mails";
        sendMail(body, receiver, subject);
    }

    private void sendMail(String body, String receiver, String subject){
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage);
        try {
            mimeMessageHelper.setFrom(sender);
            mimeMessageHelper.setTo(receiver);
            mimeMessageHelper.setText(body, true);
            mimeMessageHelper.setSubject(subject);
            javaMailSender.send(mimeMessage);
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }
}
