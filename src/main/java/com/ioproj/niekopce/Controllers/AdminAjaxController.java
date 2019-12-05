package com.ioproj.niekopce.Controllers;

import com.ioproj.niekopce.Model.Certification;
import com.ioproj.niekopce.Model.DTO.CertificationDTO;
import com.ioproj.niekopce.Repositories.CertificationRepository;
import com.ioproj.niekopce.Services.CertificationService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MimeTypeUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin/ajax")
@AllArgsConstructor
public class AdminAjaxController {

    private final CertificationRepository certificationRepository;
    private  final CertificationService certificationService;

    @RequestMapping(value = "/testlist", method = RequestMethod.GET)
    public @ResponseBody
    List<CertificationDTO> getAjax() {
        System.out.println("Inside here");
        List<CertificationDTO> certifications = certificationService.getAllCertifications();
        System.out.println(certifications.size());
        return certifications;
    }

}
