package com.ioproj.niekopce.Model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Certification {

    @Id
    @GeneratedValue
    private Long certificationDbID;
    private String nextVisitDate;
    private Boolean isFinished;
    @OneToMany      //TODO: Przerobić na lepsze powiązanie
    private List<Visit> visits;



    public void addVisitReview(Visit visit){
        visits.add(visit);
        visit.setCertification(this);
    }
}
