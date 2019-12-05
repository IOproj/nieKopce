package com.ioproj.niekopce.Repositories;

import com.ioproj.niekopce.Model.Certification;
import com.ioproj.niekopce.Model.Visit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

import static org.hibernate.loader.Loader.SELECT;

@Repository
public interface VisitRepository extends JpaRepository<Visit,Long> {


    @Query("SELECT v FROM Visit v where v.certification=:certification")
    List<Visit> getAllCertificationVisits(@Param("certification")Certification certification);

}
