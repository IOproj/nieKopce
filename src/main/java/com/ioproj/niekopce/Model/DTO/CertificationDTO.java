package com.ioproj.niekopce.Model.DTO;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Builder
@Getter
@Setter
public class CertificationDTO  implements Serializable {

    private String certificationID;
    private String nextVisitDate;
    private Boolean isFinished;
}
