package com.ioproj.niekopce.Controllers;


import com.ioproj.niekopce.Model.Certification;
import com.ioproj.niekopce.Model.DTO.AddUserDTO;
import com.ioproj.niekopce.Services.ServicemanService;
import com.ioproj.niekopce.Services.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;
import java.util.List;

@Controller
@RequestMapping("/user")
@AllArgsConstructor
public class UserController {

    private UserService userService;
    private ServicemanService servicemanService; //TODO: osobny kontroler dla serwisanta


    @GetMapping("/addUser")
    String addMedPage(Model model) {
        model.addAttribute("addUserDTO", new AddUserDTO());
        return "user/register";
    }

    @GetMapping("/standard")
    String getUserPage(Model model) {
        model.addAttribute("addUserDTO", new AddUserDTO());
        return "main/userPage";
    }

    @GetMapping("/sendApplication")
    String sendNewCertificationRequest(Principal principal) {
        String name = principal.getName();
        userService.addCertification(name);
        return "main/userPage";
    }

    @PostMapping("/addUser")
    String addMed(@ModelAttribute @Valid AddUserDTO addUserDTO, Errors errors, Model model) {
        if (errors.hasErrors()) {
            model.addAttribute("title", "Add User");
            return "user/register";
        }
        userService.addUser(addUserDTO);
        return "redirect:/main/mainPage";
    }
}
