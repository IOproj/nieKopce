package com.ioproj.niekopce.Services;

import com.ioproj.niekopce.Model.Certification;
import com.ioproj.niekopce.Model.Visit;
import com.ioproj.niekopce.Repositories.CertificationRepository;
import com.ioproj.niekopce.Repositories.VisitRepository;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.LinkedList;
import java.util.List;


@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@RunWith(SpringRunner.class)
@DataJpaTest
public class VisitServiceTest {

    @Autowired
    private CertificationRepository certificationRepository;
    @Autowired
    private VisitRepository visitRepository;

    @Before
    public void createCertification() {
        List<Certification> resultList = certificationRepository.findAll();
        System.out.println(resultList.size());
        Certification certification = new Certification(100L, null, false, new LinkedList<Visit>(), null);
        certificationRepository.save(certification);
    }

    @Before
    public void createVisit() {
        List<Visit> resultList = visitRepository.findAll();
        System.out.println(resultList.size());
        Visit visit = new Visit(99L, certificationRepository.findCertificationById(100L), "20190315", "testComment");
        visitRepository.save(visit);
    }

    @Test
    public void checkRepo(){
        List<Visit> resultList = visitRepository.findAll();
        System.out.println(resultList.size());
        Assert.assertEquals(2,resultList.size());
    }

    @Test
    public void findVisitsForCertification(){
        List<Visit> resultList=visitRepository.getAllCertificationVisits(certificationRepository.findCertificationById(100L));
        System.out.println(resultList.size());
        Assert.assertEquals(1,resultList.size());
    }


    //TODO: Zrobić test pobierania wizyty dla certyfikacji


}
