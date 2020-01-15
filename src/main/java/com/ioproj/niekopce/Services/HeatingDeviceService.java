package com.ioproj.niekopce.Services;

import com.ioproj.niekopce.Repositories.HeatingDeviceRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class HeatingDeviceService {
    private final HeatingDeviceRepository heatingDeviceRepository;
}
