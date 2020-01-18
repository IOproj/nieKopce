package com.ioproj.niekopce.Services;

import com.ioproj.niekopce.Model.Certification;
import com.ioproj.niekopce.Repositories.CertificationRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ServicemanService {
    private CertificationRepository certificationRepository;

    public List<Certification> getAllCertifications(){
        return  certificationRepository.findAll();
    }

    public Certification getCertificationById(Long id){
        return  certificationRepository.findCertificationById(id);
    }

    public List<Certification> getNotHandledCertifications(){
        return certificationRepository.finAllNotHandled();
    }

    public void setNextVisitDate(Long id,String nextVisit){
        Certification certification = certificationRepository.findCertificationById(id);
        certification.setNextVisitDate(nextVisit);
        certificationRepository.save(certification);
    }
}
