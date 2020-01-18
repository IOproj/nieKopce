package com.ioproj.niekopce.Model.DTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;


@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class HeatingDeviceDTO {

    private String producer;
    private String yearOfProduction;
    private String warrantyTerminationDate;
    private String fuel;
    private String otherComments;
}
