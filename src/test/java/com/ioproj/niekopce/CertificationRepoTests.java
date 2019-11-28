package com.ioproj.niekopce;

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

@AutoConfigureTestDatabase(replace= AutoConfigureTestDatabase.Replace.NONE)
@RunWith(SpringRunner.class)
@DataJpaTest
public class CertificationRepoTests {

    @Autowired
    private CertificationRepository certificationRepository;

    @Autowired
    private VisitRepository visitRepo;

    @Before
    public void createCertification(){
        Certification certification = new Certification();
        certification.setId(100L);
        certification.setIsFinished(false);
        certification.setNextVisitDate("newDate");
        certification.setVisits(new LinkedList<Visit>());
        certificationRepository.save(certification);
    }

    @Test
    public void addCertification(){
        Certification certification = new Certification();
        certification.setId(100L);
        certification.setIsFinished(false);
        certification.setNextVisitDate("newDate");
        certification.setVisits(new LinkedList<Visit>());
        certificationRepository.save(certification);
        Assert.assertEquals(1L,certificationRepository.count());
    }

    @Test
    public void addVisitToCertification(){
      Visit visit = new Visit();
      visit.setVisitId(101L);
      visit.setComment("Wizyta testowa");
      visit.setDate("20190514");
      visit.setCertification(certificationRepository.findCertificationById(100L));
      visitRepo.save(visit);
      Certification certification1 = certificationRepository.findCertificationById(100L);
      certification1.addVisitReview(visit);
      for(Visit visit1:certification1.getVisits()){
          System.out.println(visit1.toString());
      }
     Assert.fail();
    }
}
