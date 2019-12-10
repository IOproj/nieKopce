package com.ioproj.niekopce.Model;

import com.ioproj.niekopce.Model.DTO.CertificationDTO;
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
    @GeneratedValue(strategy = GenerationType.AUTO)// wyłączone na potrzeby testów, ale docelowo można ustawiać takie ID jakie ma klient
    private Long id;
    private String nextVisitDate;
    private Boolean isFinished;
    @OneToMany(mappedBy = "certification",   //  'mappedBy = "certification"' oznacza,że 'private Certification certification' w
            cascade = CascadeType.ALL, // klasie Visit odpowiada za relację (zawiera klucz obcy do query by znaleźć wszystkie wizyty dla danej certyfikacji
            orphanRemoval = true)
    private List<Visit> visits;

    @OneToOne(mappedBy = "certification")
    @JoinColumn(name = "db_id",unique = true)
    private UserAccount userAccount;


    public void addVisitReview(Visit visit) {
        visits.add(visit);
        visit.setCertification(this);
    }

    public CertificationDTO dto(){
        return  CertificationDTO.builder()
                .nextVisitDate(this.nextVisitDate)
                .isFinished(this.isFinished)
                .certificationID(this.id.toString())
                .build();
    }


}
