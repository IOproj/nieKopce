package com.ioproj.niekopce.Model;


import com.ioproj.niekopce.Model.DTO.VisitDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Entity
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Visit {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)// wyłączone na potrzeby testów
    private Long visitId;

    @ManyToOne(fetch = FetchType.LAZY)
    //@JoinColumn(name = "certification_id")
    private Certification certification;
    private String date;
    private String comment;

    @Override
    public String toString() {
        return "Id:"+visitId+" data:"+date+" comment:"+comment;
    }

    public VisitDTO dto(){
        return  VisitDTO.builder()
                .comment(this.comment)
                .date(this.date)
                .build();
    }
}
