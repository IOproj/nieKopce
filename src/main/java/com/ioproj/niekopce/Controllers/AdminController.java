package com.ioproj.niekopce.Controllers;

import com.ioproj.niekopce.Model.Certification;
import com.ioproj.niekopce.Model.DTO.AddUserDTO;
import com.ioproj.niekopce.Services.ServicemanService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.security.Principal;
import java.util.List;

@Controller
@RequestMapping("/admin")
@AllArgsConstructor
public class AdminController {

    private ServicemanService servicemanService;

    @GetMapping("/adminPage")
    String getAdminPage(Model model) {
        model.addAttribute("addUserDTO", new AddUserDTO());
        return "admin/adminPage";
    }

    @GetMapping("/getApplications")
   String getApplications(Principal principal) {
        String name = principal.getName();
        System.out.println(name);
        List<Certification> certifications = servicemanService.getAllCertifications();
        for(Certification certification:certifications){
            System.out.println(certification.getNextVisitDate());
        }
       return  "admin/adminPage";
    }

    @GetMapping("/planVisit")
    String addIncomingVisit(@RequestParam("certificationID") String certificationID,@RequestParam("inputDate") String visitDate){
        servicemanService.setNextVisitDate(Long.valueOf(certificationID),visitDate);
        return "admin/adminPage";
    }
}
