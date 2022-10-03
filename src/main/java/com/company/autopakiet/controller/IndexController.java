package com.company.autopakiet.controller;

import com.company.autopakiet.dao.IUserDAO;
import com.company.autopakiet.email.NotifyMailService;
import com.company.autopakiet.entity.Car;
import com.company.autopakiet.service.ICarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
public class IndexController {

    @Autowired
    NotifyMailService mailService;
    @Autowired
    ICarService carService;
    @Autowired
    IUserDAO userDAO;

    private boolean sentMailNotify = false;

    @RequestMapping("/")
    public String showIndex (Model carModel){
        if (!sentMailNotify){
            sendMailNotify();
        }

        Car car = new Car();
        car.setServiceCode(0);
        carModel.addAttribute("car", car);

        return "index";
    }

    private void sendMailNotify (){
        List<Car> expiredInspection = carService.getExpiredIn7DaysInspection();
        List<Car> expiredInsurance = carService.getExpiredIn7DaysInsurance();
        String email;

        if (expiredInspection.size() > 0){
            for (Car car: expiredInspection){
                System.out.println(car);
                email = userDAO.geEmailByName(car.getUserName());
                mailService.sendInspectionNotification(email, car);
                System.out.println("Sending Inspection NOTIFY EMAIL");
            }
        }

        if (expiredInsurance.size() > 0 ){
            for (Car car: expiredInsurance){
                email = userDAO.geEmailByName(car.getUserName());
                mailService.sendInsuranceNotification(email, car);
                System.out.println("Sending INSURANCE NOTIFY EMAIL");
            }
        }
        sentMailNotify = true;
    }
}
