package com.ioproj.niekopce.Repositories;

import com.ioproj.niekopce.Model.HeatingDevice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HeatingDeviceRepository extends JpaRepository<HeatingDevice,Long> {
}
