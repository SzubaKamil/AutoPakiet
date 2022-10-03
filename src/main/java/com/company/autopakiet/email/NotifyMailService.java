package com.company.autopakiet.email;

import com.company.autopakiet.entity.Car;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class NotifyMailService extends MailService{

    public NotifyMailService(JavaMailSender javaMailSender) {
        super(javaMailSender);
    }

    public void sendInspectionNotification (String userEmail, Car car){
        String subject = "Powiadomonie o końcu przeglądu";
        String content = "Przegląd w aucie " + car.getProducer() + " " + car.getModel() +
                " kończy się dnia: " +  car.getCarInspections().get(car.getCarInspections().size()-1).getExpirationDate();

        sendEmail(userEmail, subject, content);
    }

    public void sendInsuranceNotification (String userEmail, Car car){
        String subject = "Powiadomonie o końcu ubezpieczenia";
        String content = "Ubezpieczenie w aucie " + car.getProducer() + " " + car.getModel() +
                " kończy się dnia: " +  car.getInsurances().get(car.getInsurances().size()-1).getEndDate();

        sendEmail(userEmail, subject, content);
    }
}
