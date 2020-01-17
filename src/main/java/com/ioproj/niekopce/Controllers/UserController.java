package com.ioproj.niekopce.Controllers;

import com.ioproj.niekopce.Model.DTO.AddUserDTO;
import com.ioproj.niekopce.Model.UserAccount;
import com.ioproj.niekopce.Services.CertificationService;
import com.ioproj.niekopce.Services.HeatingDeviceService;
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
public class UserController {           //TODO: ten Controller ma za dużo w sobie, trzeba by przerobić, rozdzielić

    private UserService userService;
    private CertificationService certificationService;
    private HeatingDeviceService heatingDeviceService;

    private

    @GetMapping("/addUser")
    String addUserPage(Model model) {
        model.addAttribute("addUserDTO", new AddUserDTO());
        return "user/register";
    }

    @GetMapping("/standard")
    String getUserPage(Model model, Principal principal) {
        String name;
        UserAccount user = null;
        try {
            name = principal.getName();
            user = userService.get(name);
        } catch (NullPointerException e) {
            name = "niezalogowany";
        }
        List<String> status = certificationService.checkUsersCertification(user);
        model.addAttribute("certificationStatus", status);
        return "main/userPage";
    }

    @GetMapping("/sendApplication")
    String sendNewCertificationRequest(Principal principal) {
        String name = principal.getName();
        userService.addCertification(name);
        return "main/userPage";
    }

    @GetMapping("/sendApplication2")
    String sendNewCertificationRequest2(Principal principal, @RequestParam("producer") String producer, @RequestParam("date") String date,
                                        @RequestParam("warranty") String warranty, @RequestParam("fuel") String fuel, @RequestParam("otherComments") String otherComments) {
        String name = principal.getName();
        heatingDeviceService.addHeatingDevice(name,producer,date,warranty,fuel,otherComments);      //TODO: To było by fajnie zrefaktorować
        userService.addCertification(name);
        return "main/userPage";
    }


    @PostMapping("/addUser")
    String addUser(@ModelAttribute @Valid AddUserDTO addUserDTO, Errors errors, Model model) {
        if (errors.hasErrors()) {
            model.addAttribute("title", "Add User");
            return "user/register";
        }
        userService.addUser(addUserDTO);
        return "redirect:/main/mainPage";
    }
}
