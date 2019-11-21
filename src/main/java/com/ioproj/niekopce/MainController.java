package com.ioproj.niekopce;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;

@Controller
@RequestMapping("/main")
public class MainController {

    @GetMapping("/mainPage")
    public String getMainPage(Model model, Principal principal) {
        String name;
        try {
            name = principal.getName();
        } catch (NullPointerException e) {
            name = "niezalogowany";
        }
        model.addAttribute("username", name);
        return "main/mainPage";
    }
}
