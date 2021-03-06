package com.demo.controller;

import com.demo.models.Administrator;
import com.demo.services.AdministratorService;
import com.demo.utility.Login;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;
import java.util.List;

@Controller
public class LoginController {

    @Autowired
    AdministratorService administratorService;


    @GetMapping("/index")
    public String getLogin(Model model) {
        Login user = new Login();

        model.addAttribute("loginUser", user);
        return "index";
    }

    /**
     * en este endpoint se valida si el login es correcto o no
     * si el metodo {@method isValidateAdmin} valida correctamente al
     * usuario la redireccion es al endpoint {@code /admin/management/loginUser/password}
     * de lo contrario la redireccion es para el endpoint {@code /index}.
     *
     * @param loginUser
     * @return
     */
    @PostMapping("/validate/admin")
    public String validateAdmin(@Valid Login loginUser, Errors errors, Model model) {
        if (errors.hasErrors()) {
            model.addAttribute("loginUser",loginUser);
            return "index";
        }

        List<Administrator> administrators = administratorService.findAll();

        if (isValidateAdmin(administrators, loginUser)) {
            return "redirect:/admin/management/" + loginUser.getUserName();
        } else {
            return "redirect:/index";
        }
    }

    /**
     * Metodo para validar el administrador para logeo
     *
     * @param administrators
     * @param loginUser
     * @return
     */
    public boolean isValidateAdmin(List<Administrator> administrators, Login loginUser) {
        boolean definition = false;

        for (Administrator administrator : administrators) {
            if (administrator.getUserName().equals(loginUser.getUserName()) &&
                    administrator.getPassword().equals(loginUser.getPassword())) {
                definition = true;
                break;
            }
        }
        return definition;
    }
}
