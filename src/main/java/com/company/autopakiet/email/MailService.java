package com.company.autopakiet.email;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

abstract class MailService {

    private JavaMailSender javaMailSender;
    private static final String FROM_MAIL_TITLE ="Auto Pakiet <auto.pakiet@company.com>";

    public MailService(JavaMailSender javaMailSender) {
        this.javaMailSender = javaMailSender;
    }

    protected void sendEmail(String userEmail, String subject, String content) {
        SimpleMailMessage msg = new SimpleMailMessage();
        msg.setTo(userEmail);
        msg.setFrom(FROM_MAIL_TITLE);

        msg.setSubject(subject);
        msg.setText(content);

        javaMailSender.send(msg);
    }
}
