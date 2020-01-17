package com.ioproj.niekopce.Model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "device")
public class HeatingDevice {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long deviceId;
    private String producer;
    private Integer yearOfProduction;
    private String warrantyTerminationDate;
    private String fuel;
    private String otherComments;

    @OneToOne(mappedBy = "device")
    private UserAccount userAccount;
}
