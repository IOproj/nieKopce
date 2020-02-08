package com.ioproj.niekopce.Controllers;

import com.ioproj.niekopce.Model.Certification;
import com.ioproj.niekopce.Model.DTO.CertificationDTO;
import com.ioproj.niekopce.Model.DTO.HeatingDeviceDTO;
import com.ioproj.niekopce.Model.DTO.VisitDTO;
import com.ioproj.niekopce.Model.HeatingDevice;
import com.ioproj.niekopce.Model.UserAccount;
import com.ioproj.niekopce.Services.CertificationService;
import com.ioproj.niekopce.Services.HeatingDeviceService;
import com.ioproj.niekopce.Services.VisitService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin/ajax")
@AllArgsConstructor
public class AdminAjaxController {

    private final CertificationService certificationService;
    private final VisitService visitService;
    private final HeatingDeviceService heatingDeviceService;

    @RequestMapping(value = "/testlist", method = RequestMethod.GET)
    public @ResponseBody
    List<CertificationDTO> getAjax() {
        return certificationService.getAllCertifications();
    }

    @RequestMapping(value = "/getVisits", method = RequestMethod.GET)
    public @ResponseBody
    List<VisitDTO> getAjaxVisit(@RequestParam("certificationID") String certificationID) {
        return visitService.getAllCertificationsVisit(Long.valueOf(certificationID));
    }

    @RequestMapping(value = "/getDetails", method = RequestMethod.GET)
    public @ResponseBody
    List<HeatingDeviceDTO> getAjaxDetails(@RequestParam("certificationID") String certificationID) {
        Certification certification = certificationService.getCertificationById(Long.valueOf(certificationID));
        UserAccount owner = certification.getUserAccount();
       return heatingDeviceService.getUserHeatingDevice(owner);
    }

    @RequestMapping(value = "/addVisit", method = RequestMethod.GET)
    public @ResponseBody
    Boolean addVisit(@RequestParam("certificationID") String certificationID, @RequestParam("comment") String comment,
                     @RequestParam("date") String date, @RequestParam("finish") String finish) {
        visitService.addVisit(certificationService.getCertificationById(Long.valueOf(certificationID)), comment, date,finish);
        return true;
    }
}
