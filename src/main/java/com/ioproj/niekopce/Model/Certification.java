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
    private Long id;
    private String nextVisitDate;
    private Boolean isFinished;
    @OneToMany(mappedBy = "certification",   //  'mappedBy = "certification"' oznacza,że 'private Certification certification' w
            cascade = CascadeType.ALL, // klasie Visit odpowiada za relację (zawiera klucz obcy do query by znaleźć wszystkie wizyty dla danej certyfikacji
            orphanRemoval = true)
    private List<Visit> visits;


    public void addVisitReview(Visit visit) {
        visits.add(visit);
        visit.setCertification(this);
    }
}
