package com.ioproj.niekopce.Services;

import com.ioproj.niekopce.Model.Certification;
import com.ioproj.niekopce.Model.DTO.CertificationDTO;
import com.ioproj.niekopce.Model.UserAccount;
import com.ioproj.niekopce.Repositories.CertificationRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class CertificationService {

    private final CertificationRepository certificationRepository;

    public List<CertificationDTO> getAllCertifications() {
        List<Certification> dbCertifications = certificationRepository.findAll();
        List<CertificationDTO> resultList = new ArrayList<CertificationDTO>();
        for (Certification certification : dbCertifications) {
            CertificationDTO dto = certification.dto();
            if(dto.getNextVisitDate()==null || dto.getNextVisitDate().equals("")){
                dto.setNextVisitDate("Brak terminu następnej wizyty");
            }
            resultList.add(dto);
        }
        return resultList;
    }

    public Certification getCertificationById(Long id) {
        return certificationRepository.findCertificationById(id);
    }

    public List<String> checkUsersCertification(UserAccount user) {
        String queryResult = certificationRepository.getCertificationStatus(user);
        List<String> statusInfo = new ArrayList<>(2);
        String status,nextVisit;
        if(queryResult == null){
            status = "niekompletne dane";
            nextVisit = "niekompletne dane";
        }
        else {
            status = queryResult.substring(0, queryResult.indexOf(','));
            nextVisit = queryResult.substring(queryResult.indexOf(',') + 1, queryResult.length());
            if (status.equals("false")) {
                status = "niezakończona";
            } else if (status.equals("true")) {
                status = "zakończona";
                nextVisit = "";
            }
            if (nextVisit.equals("null") && status.equals("niezakończona")) {
                nextVisit = "Termin wizyty nieustalony";
            }
        }
        statusInfo.add(status);
        statusInfo.add(nextVisit);
        return statusInfo;
    }
}
