package com.ioproj.niekopce.Model;


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

    //TODO: Powiązać to z Certification obustronnym OneToMany

    @Id
    @GeneratedValue
    private Long visitId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "certification_id")
    private Certification certification;
    private String date;
    private String comment;

    @Override
    public String toString() {
        return "Id:"+visitId+" data:"+date+" comment:"+comment;
    }
}
