package com.ioproj.niekopce.Model.DTO;


import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class VisitDTO {

    private String date;
    private String comment;

}
