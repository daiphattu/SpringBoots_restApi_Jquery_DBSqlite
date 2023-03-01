package com.project.hant.dtos;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class PatientDTO extends BaseDTO{
    private String namePatient;

    private String genderPatient;

    private String birthdayPatient;

    private String addressPatient;

    private String phonePatient;

    private String idCard;
}
