package com.ioproj.niekopce.Repositories;

import com.ioproj.niekopce.Model.Certification;
import com.ioproj.niekopce.Model.UserAccount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface CertificationRepository extends JpaRepository<Certification, Long> {

    @Query("SELECT c FROM Certification c where c.id = :id")
    Certification findCertificationById(@Param("id") Long id);

    @Query("SELECT c FROM Certification c where c.nextVisitDate IS NULL")
    List<Certification> finAllNotHandled();

    @Query(value = "SELECT c.isFinished,c.nextVisitDate FROM Certification c where c.userAccount =:userAccount")
    String getCertificationStatus(@Param("userAccount") UserAccount userID);

    @Modifying
    @Transactional
    @Query("UPDATE Certification cr SET cr.isFinished = true WHERE cr.id = :certification")
    void setAsFinished(@Param("certification")Long certification);
}
