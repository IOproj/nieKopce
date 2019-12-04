package com.ioproj.niekopce.Services;

import com.ioproj.niekopce.Model.Certification;
import com.ioproj.niekopce.Model.Visit;
import com.ioproj.niekopce.Repositories.CertificationRepository;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.LinkedList;
import java.util.List;

import static org.junit.Assert.*;

@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@RunWith(SpringRunner.class)
@DataJpaTest
public class ServicemanServiceTest {

    @Autowired
    private CertificationRepository certificationRepository;

    @TestConfiguration
    static class EmployeeServiceImplTestContextConfiguration {

        @Bean
        public ServicemanService servicemanService() {
            return new ServicemanService();
        }
    }

    @Autowired
    private ServicemanService service;

    @Before
    public void createCertification() {
        List<Certification> resultList = certificationRepository.findAll();
        System.out.println(resultList.size());
        Certification certification = new Certification(100L, null, false, new LinkedList<Visit>(), null);
        certificationRepository.save(certification);
    }

    @Test
    public void shouldFindOneObjectWhenNoModificationMade() {
        List<Certification> resultList = certificationRepository.finAllNotHandled();
        Assert.assertEquals(1, resultList.size());
    }

    @Test
    public void shouldFindNoObjectWhenAllModified() {
        Certification certification = certificationRepository.findCertificationById(100L);
        certification.setNextVisitDate("chengedDate");
        List<Certification> resultList = certificationRepository.finAllNotHandled();
        Assert.assertEquals(0, resultList.size());

    }

}