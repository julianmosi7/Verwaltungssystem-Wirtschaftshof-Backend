package com.example.backend_verwaltungssoftware.Mail;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailSenderService {

    @Autowired
    private JavaMailSender javaMailSender;

    public void sendEmail(String toMail, String text, String title){
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();

        simpleMailMessage.setTo(toMail);
        simpleMailMessage.setFrom("wirtschaftshofaschachtal@gmail.com");
        simpleMailMessage.setText(text);
        simpleMailMessage.setSubject(title);
        javaMailSender.send(simpleMailMessage);

        System.out.println("Mail sent!");
    }
}
