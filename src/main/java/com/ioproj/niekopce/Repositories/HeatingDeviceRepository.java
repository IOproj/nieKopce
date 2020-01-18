package com.ioproj.niekopce.Repositories;

import com.ioproj.niekopce.Model.HeatingDevice;
import com.ioproj.niekopce.Model.UserAccount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HeatingDeviceRepository extends JpaRepository<HeatingDevice,Long> {


    @Query("SELECT hd FROM HeatingDevice hd where hd.userAccount =:owner")
    List<HeatingDevice> getUserHeatingDevice(UserAccount owner);
}
