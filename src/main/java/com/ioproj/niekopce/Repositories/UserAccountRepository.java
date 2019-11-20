package com.ioproj.niekopce.Repositories;


import com.ioproj.niekopce.Model.UserAccount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserAccountRepository  extends JpaRepository<UserAccount, Long> {


    @Query("SELECT ua FROM UserAccount ua WHERE ua.username = :username")
    Optional<UserAccount> findByUsername(@Param("username") String username);
}
