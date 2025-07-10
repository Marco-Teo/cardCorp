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
                "\nCordiali saluti. Il team di Card Corp");
        javaMailSender.send(messaggio);
    }


    public void modificaUtente(User user) {
        if (user.getEmail() == null || user.getEmail().isBlank()) return;

        SimpleMailMessage messaggio = new SimpleMailMessage();
        messaggio.setTo(user.getEmail());
        messaggio.setSubject("Benvenuto in Card Corp!");
        messaggio.setText("Ciao " + user.getUserName() + ", " +
                "il tuo account è stato modificato con successo, " +
                "se non sei stato tu a richiedere questa modifica contattataci immediatamente "+
                "\nCordiali saluti. Il team di Card Corp");
        javaMailSender.send(messaggio);
    }

    public void utenteEliminato(User user) {
        if (user.getEmail() == null || user.getEmail().isBlank()) return;

        SimpleMailMessage messaggio = new SimpleMailMessage();
        messaggio.setTo(user.getEmail());
        messaggio.setSubject("Account eliminato - Card Corp");
        messaggio.setText("Ciao " + user.getUserName() + ", " +
                "il tuo account su Card Corp è stato eliminato con successo. " +
                "Se non sei stato tu a richiedere questa operazione, ti invitiamo a contattarci immediatamente. " +
                "\n\nGrazie per averci scelto. \nIl team di Card Corp");

        javaMailSender.send(messaggio);
    }

}