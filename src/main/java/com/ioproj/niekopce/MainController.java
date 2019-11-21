package com.ioproj.niekopce;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/main")
public class MainController {

    @GetMapping("/mainPage")
    public String getMainPage(){
        return "main/mainPage";
    }

    @GetMapping("/test")
    public String getTestLog(){
        System.out.println("AAAAAAAAAAAAAAAAAA");
        return "main/mainPage";
    }
}
