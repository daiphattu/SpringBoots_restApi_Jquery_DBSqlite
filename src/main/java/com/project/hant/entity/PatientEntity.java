package com.project.hant.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

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

    @Column(name = "insurance_patient", nullable = false)
    private String InsurancePatient;

    @Column(name = "id_card", nullable = false)
    private String idCard;


}
