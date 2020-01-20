package com.ioproj.niekopce.Services;

import com.ioproj.niekopce.Model.Certification;
import com.ioproj.niekopce.Model.DTO.VisitDTO;
import com.ioproj.niekopce.Model.Visit;
import com.ioproj.niekopce.Repositories.CertificationRepository;
import com.ioproj.niekopce.Repositories.VisitRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class VisitService {

    private final VisitRepository visitRepository;
    private final CertificationRepository certificationRepository; //TODO certificationRepository albo dla obu metod albo wcale

    public List<VisitDTO> getAllCertificationsVisit(Long certificationID){
        Certification certification = certificationRepository.findCertificationById(certificationID);
        List<Visit> visits = visitRepository.getAllCertificationVisits(certification);
        List<VisitDTO> resultList = new ArrayList<>();
        for(Visit visit:visits){
            resultList.add(visit.dto());
        }
        return resultList;
    }

    public void addVisit(Certification certificationById, String comment, String date,String finish) {
        Visit visit = new Visit();
        visit.setCertification(certificationById);
        visit.setComment(comment);
        visit.setDate(date);
        visitRepository.save(visit);
        if(finish.equals("true")){
            certificationById.setIsFinished(true);
            certificationRepository.setAsFinished(certificationById.getId());
        }
    }
}
