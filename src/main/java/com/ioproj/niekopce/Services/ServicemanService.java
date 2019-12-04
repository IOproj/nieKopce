package com.ioproj.niekopce.Services;

import com.ioproj.niekopce.Model.Certification;
import com.ioproj.niekopce.Repositories.CertificationRepository;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
@NoArgsConstructor
public class ServicemanService {
    private  CertificationRepository certificationRepository;


    public List<Certification> getNotHandledCertifications(){
        List<Certification> notHandled = certificationRepository.finAllNotHandled();
        return  notHandled;
    }

    public void setNextVisitDate(Long id){
        Certification certification = certificationRepository.findCertificationById(id);
        certification.setNextVisitDate("chengedDate");
        certificationRepository.save(certification);
    }
}
