package com.ioproj.niekopce.Services;

import com.ioproj.niekopce.Model.DTO.HeatingDeviceDTO;
import com.ioproj.niekopce.Model.HeatingDevice;
import com.ioproj.niekopce.Model.UserAccount;
import com.ioproj.niekopce.Repositories.HeatingDeviceRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;

@Service
@AllArgsConstructor
public class HeatingDeviceService {
    private final HeatingDeviceRepository heatingDeviceRepository;
    private final UserService userService;

    public void addHeatingDevice(String name, String producer, String date, String warranty, String fuel, String otherComments) {
        UserAccount toFind = userService.get(name);
        HeatingDevice heatingDevice = new HeatingDevice();
        heatingDevice.setUserAccount(toFind);
        heatingDevice.setProducer(producer);
        heatingDevice.setYearOfProduction(Integer.valueOf(date));
        heatingDevice.setWarrantyTerminationDate(warranty);
        heatingDevice.setFuel(fuel);
        heatingDevice.setOtherComments(otherComments);
        heatingDeviceRepository.save(heatingDevice);
        toFind.setDevice(heatingDevice);
        userService.updateHeatingDevice(heatingDevice,toFind.getUsername());
    }

    public List<HeatingDeviceDTO> getUserHeatingDevice(UserAccount owner) {
        List<HeatingDevice> resultList =  heatingDeviceRepository.getUserHeatingDevice(owner);
        List<HeatingDeviceDTO> outputList = new LinkedList<>();
        for(HeatingDevice device :resultList){
            outputList.add(device.dto());
        }
        return outputList;
    }
}
