package com.ioproj.niekopce.Repositories;

import com.ioproj.niekopce.Model.Certification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CertificationRepository extends JpaRepository<Certification,Long> {

    @Query("SELECT c FROM Certification c where c.id = :id")
     Certification findCertificationById(@Param("id") Long id);

    @Query("SELECT c FROM Certification c where c.nextVisitDate IS NULL")
    List<Certification> finAllNotHandled();
}
