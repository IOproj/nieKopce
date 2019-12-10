package com.ioproj.niekopce.Model.DTO;


import lombok.Builder;
import lombok.Getter;

import java.io.Serializable;

@Builder
@Getter
public class VisitDTO implements Serializable {

    private String date;
    private String comment;

}
