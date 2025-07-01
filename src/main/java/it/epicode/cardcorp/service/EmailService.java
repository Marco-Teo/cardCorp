package it.epicode.cardcorp.service;

import it.epicode.cardcorp.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

    @Autowired
    private JavaMailSender javaMailSender;

    public void inviaBenvenuto(User user) {
        if (user.getEmail() == null || user.getEmail().isBlank()) return;

        SimpleMailMessage messaggio = new SimpleMailMessage();
        messaggio.setTo(user.getEmail());
        messaggio.setSubject("Benvenuto in Card Corp!");
        messaggio.setText("Ciao " + user.getUserName() + ", " +
                "grazie per esserti registrato al nostro e-commerce. " +
                "Siamo felici di averti con noi, ti auguriamo di poter trovare le carte che stai cercando "+
                "Cordiali saluti. Il team di Card Corp");
        javaMailSender.send(messaggio);
    }
}