package com.ioproj.niekopce;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/main")
public class MainController {

    boolean isGituwa = false;

    @GetMapping("/mainPage")
    public String getMainPageAndGituwa(){

        return "main/mainPage";
    }

}
