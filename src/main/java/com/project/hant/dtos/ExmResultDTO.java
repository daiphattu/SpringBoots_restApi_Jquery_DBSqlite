package com.project.hant.dtos;

import com.project.hant.entity.BaseEntity;
import com.project.hant.entity.PatientEntity;
import jakarta.persistence.Column;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class ExmResultDTO extends BaseEntity{

    private String leftEye;

    private String rightEye;

    private String note;

    private PatientEntity patient;
}
