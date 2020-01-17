package com.ioproj.niekopce;

import com.ioproj.niekopce.Model.Certification;
import com.ioproj.niekopce.Model.UserAccount;
import com.ioproj.niekopce.Model.Visit;
import com.ioproj.niekopce.Repositories.CertificationRepository;
import com.ioproj.niekopce.Repositories.UserAccountRepository;
import com.ioproj.niekopce.Repositories.VisitRepository;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.jws.soap.SOAPBinding;
import java.util.LinkedList;
import java.util.UUID;

@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@RunWith(SpringRunner.class)
@DataJpaTest
public class CertificationRepoTests {

    @Autowired
    private CertificationRepository certificationRepository;

    @Autowired
    private VisitRepository visitRepo;

    @Autowired
    private UserAccountRepository userAccountRepository;

    @Before
    public void createCertification() {
        Certification certification = new Certification(100L,"nextVisit",false,new LinkedList<Visit>(),null);
        certificationRepository.save(certification);
    }

    @Before
    public void createUserAccount() {
        UserAccount userAccount = new UserAccount(99L, UUID.randomUUID(), "testUser", "testPassword",
                                                        "email@test.com", false, null,null);
        userAccountRepository.save(userAccount);
    }

    @Before
    public  void createVisit(){
        Visit visit = new Visit(10L,null,"20190612","TestVisit");
        visitRepo.save(visit);
    }


    @Test
    public void Should_ReturnValidUserAndHisCertificationInfo_NewCertificationAddedToExistingUser() {
        UserAccount requestingCertification = userAccountRepository.findByUsername("testUser").orElse(null);
        requestingCertification.setCertification(certificationRepository.findCertificationById(100L));
        //TODO dokończyć ten test
    }

    @Test
    public void addVisitToCertification() {
        Visit toAdd = visitRepo.findById(10L).orElseGet(null);
        toAdd.setCertification(certificationRepository.findCertificationById(100L));
        //TODO napisac Query do update wizyty
        Certification certification1 = certificationRepository.findCertificationById(100L);
        certification1.addVisitReview(toAdd);
        for (Visit visit1 : certification1.getVisits()) {   //TODO zamienić te for na prawidłową assercję
            System.out.println(visit1.toString());
        }
        Assert.fail();
    }
}
