package com.company.autopakiet.controller;

import com.company.autopakiet.entity.ServiceRepair;
import com.company.autopakiet.entity.User;
import com.company.autopakiet.service.IServiceRepairService;
import org.hibernate.event.spi.SaveOrUpdateEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class AdminController {

    @Autowired
    IServiceRepairService serviceRepairService;

    @RequestMapping("/admin/nowe_konto_serwis")
    public String showNewServiceAccount (Model model) {
        User user = new User();
        user.setAuthority("ROLE_SERWIS");
        model.addAttribute("user", user);
        return "/users/new-account";
    }

    @RequestMapping("/admin/nowy_typ_naprawy_serwisowej")
    public String showNewServiceRepair (Model model) {
        ServiceRepair serviceRepair = new ServiceRepair();
        model.addAttribute("serviceRepair", serviceRepair);

        return "/users/admin/new-service-type";
    }

    @RequestMapping("newServiceType")
    public String newServiceRepair (@ModelAttribute("serviceRepair")ServiceRepair serviceRepair,  Model model){
        String description = "Nowy typ naprawy serwisowej został dodany!!!";

        // save service repair to database if success result = true
        boolean result = serviceRepairService.save(serviceRepair);

        if (!result){
            description = "Niestety nie udało się dodać nowego typu naprawy serwisowej!!";
        }

        model.addAttribute("description", description);
        model.addAttribute("result", result);
        return "/users/new-account-result";
    }

}
