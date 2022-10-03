package com.company.autopakiet.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class LoginController {

    @RequestMapping("/login")
    public String showMyLoginPage (){
        return "login";
    }

    @GetMapping("/access-denied")
    public String showAccessDenied (){
        return "access-denied";
    }

}
