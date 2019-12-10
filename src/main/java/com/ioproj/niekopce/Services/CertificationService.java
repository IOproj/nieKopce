package com.ioproj.niekopce.Services;

import com.ioproj.niekopce.Model.Certification;
import com.ioproj.niekopce.Model.DTO.CertificationDTO;
import com.ioproj.niekopce.Repositories.CertificationRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class CertificationService {

    private final CertificationRepository certificationRepository;

    public List<CertificationDTO> getAllCertifications(){
        List<Certification> dbCertifications = certificationRepository.findAll();
        List<CertificationDTO> resultList = new ArrayList<CertificationDTO>();
        for(Certification certification:dbCertifications){
            resultList.add(certification.dto());
        }
        return  resultList;
    }

    public Certification getCertificationById(Long id){
        return  certificationRepository.findCertificationById(id);
    }
}
