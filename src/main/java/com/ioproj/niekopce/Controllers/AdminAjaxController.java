package com.ioproj.niekopce.Controllers;

import com.ioproj.niekopce.Model.DTO.CertificationDTO;
import com.ioproj.niekopce.Services.CertificationService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin/ajax")
@AllArgsConstructor
public class AdminAjaxController {

    private  final CertificationService certificationService;

    @RequestMapping(value = "/testlist", method = RequestMethod.GET)
    public @ResponseBody
    List<CertificationDTO> getAjax() {
        return certificationService.getAllCertifications();
    }

}
