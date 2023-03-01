package com.project.hant.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Entity
@Getter
@Setter
@ToString
@Table(name = "patient")
public class PatientEntity extends BaseEntity {
    @Column(name = "name_patient", nullable = false)
    private String namePatient;

    @Column(name = "gender_patient", nullable = false)
    private String genderPatient;

    @Column(name = "birthday_patient", nullable = false)
    private String birthdayPatient;

    @Column(name = "address_patient", nullable = false)
    private String addressPatient;

    @Column(name = "phone_patient", nullable = false)
    private String phonePatient;

    @Column(name = "id_card", nullable = false)
    private String idCard;

    @OneToMany(mappedBy = "patient", cascade = CascadeType.ALL)
    private List<ExmResultEntity> resultEntities;
}
