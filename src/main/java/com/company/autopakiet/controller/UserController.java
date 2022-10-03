package com.company.autopakiet.controller;

import com.company.autopakiet.entity.User;
import com.company.autopakiet.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class UserController {

    @Autowired
    IUserService userService;

    @RequestMapping("/zmiana_hasla")
    public String showPasswdChange (Model model){
        User user = new User();
        model.addAttribute("user", user);

        return "/users/passwd-change";
    }

    @RequestMapping("changePasswd")
    public String changePassword (@ModelAttribute("user") User user, Model model){
        boolean result = false;
        String description = "Niestety nie udało się zmienić hasła!!!";

        // verify is pass are equals
        if (user.getPassword().equals(user.getPasswordRepeat())){

            //get current username
            String username = SecurityContextHolder.getContext().getAuthentication().getName();

            // get user by username from db
            User userFromDb = userService.getByUsername(username);
            System.out.println(userFromDb.getPassword());

            // set new passwd
            userFromDb.setPassword("{noop}" + user.getPassword());
            System.out.println(userFromDb.getPassword());

            // update user in db
            result = userService.saveOrUpdate(userFromDb);
            if (result){
                description = "Hasło zostało zmienione pomyślnie";
            }
        }
        model.addAttribute("result", result);
        model.addAttribute("description", description);

        return "/users/new-account-result";
    }

    @RequestMapping("/rejestracja")
    public String showRegisterPage (Model model){
        User user = new User();
        user.setAuthority("ROLE_USER");
        model.addAttribute("user", user);
        return "/users/new-account";
    }

    @RequestMapping("newAccount")
    public String newServiceAccount (@ModelAttribute("user") User user,  Model model){
        String description = "Nowy użytkownik został dodany pomyślnie.";
        System.out.println(user.getAuthority());
        user.setAuthority("ROLE_USER");
        user.setPassword("{noop}" + user.getPassword());

        // save new user to db -> if success result = true
        boolean result = userService.saveOrUpdate(user);
        if (!result){
            description = "UŻYTKOWNIK O TAKIEJ NAZWIE JUŻ ISTNIEJE W BAZIE LUB HASŁA NIE SĄ IDENTYCZNE !!!";
        }

        model.addAttribute("result", result);
        model.addAttribute("description", description);
        return "/users/new-account-result";
    }
}
