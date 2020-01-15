package com.ioproj.niekopce.Controllers;

import com.ioproj.niekopce.Model.DTO.CertificationDTO;
import com.ioproj.niekopce.Model.DTO.VisitDTO;
import com.ioproj.niekopce.Repositories.VisitRepository;
import com.ioproj.niekopce.Services.CertificationService;
import com.ioproj.niekopce.Services.VisitService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/admin/ajax")
@AllArgsConstructor
public class AdminAjaxController {

    private  final CertificationService certificationService;
    private  final VisitService visitService;

    @RequestMapping(value = "/testlist", method = RequestMethod.GET)
    public @ResponseBody
    List<CertificationDTO> getAjax() {
        return certificationService.getAllCertifications();
    }

    @RequestMapping(value = "/getVisits", method = RequestMethod.GET)
    public @ResponseBody
    List<VisitDTO> getAjaxVisit(@RequestParam("certificationID")String  certificationID) {
        System.out.println(certificationID);
        return visitService.getAllCertificationsVisit(Long.valueOf(certificationID));
    }

    @RequestMapping(value = "/addVisit", method = RequestMethod.GET)
    public @ResponseBody
    Boolean addVusut(@RequestParam("certificationID")String certificationID,@RequestParam("comment") String comment,
                     @RequestParam("date")String date, @RequestParam("finish")String finish){
        System.out.println(finish);
        visitService.addVisit(certificationService.getCertificationById(Long.valueOf(certificationID)),comment,date);
        return true;
    }
}
