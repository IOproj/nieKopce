package com.ioproj.niekopce.Repositories;


import com.ioproj.niekopce.Model.Certification;
import com.ioproj.niekopce.Model.UserAccount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
public interface UserAccountRepository extends JpaRepository<UserAccount,Long> {

    @Query("SELECT ua FROM UserAccount ua WHERE ua.username = :username")
    Optional<UserAccount> findByUsername(@Param("username") String username);

    @Modifying
    @Transactional
    @Query("UPDATE UserAccount ua SET ua.certification = :certification WHERE ua.username = :username")
    void updateCertification(@Param("certification")Certification certification,@Param("username") String username);

    @Query("SELECT ua.dbId FROM UserAccount ua WHERE ua.username = :username")
    Long getDbIdByUsername(@Param("username")String username);
}
