package com.ioproj.niekopce.Controllers;


import com.ioproj.niekopce.Model.DTO.AddUserDTO;
import com.ioproj.niekopce.Model.UserAccount;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Map;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/user")
public class UserController {


    @GetMapping("/addUser")
    String addMedPage(Model model) {
        model.addAttribute("addUserDTO", new AddUserDTO());
        return "user/register";
    }

    @PostMapping("/addUser")
    String addMed(@ModelAttribute @Valid AddUserDTO addUserDTO, Errors errors, Model model) {
        if (errors.hasErrors()) {
            model.addAttribute("title", "Add User");
            return "user/register";
        }
        System.out.println(addUserDTO.getEmail());
        //TODO: Logika dodawania do repozytorium
        //TODO: Zmienić tego returna by nie rzucał na whitepage
        return "redirect:";
    }
}
