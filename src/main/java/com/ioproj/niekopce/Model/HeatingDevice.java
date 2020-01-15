package com.ioproj.niekopce.Model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class HeatingDevice {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long deviceId;
    private String producer;
    private Integer yearOfProduction;
    private String warrantyTerminationDate;
    private String fuel;
    private String otherComments;
}
