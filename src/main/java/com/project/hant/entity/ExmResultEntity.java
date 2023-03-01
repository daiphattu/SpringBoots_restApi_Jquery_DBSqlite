package com.project.hant.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@ToString
@Table(name = "exmResult")
public class ExmResultEntity extends BaseEntity{
    @Column(name = "left_eye", nullable = false)
    private String leftEye;

    @Column(name = "right_eye", nullable = false)
    private String rightEye;

    @Column(name = "note", nullable = false)
    private String note;

    @ManyToOne
    @JoinColumn(name = "patient_id") // join với bảng patient thông qua khóa ngoại patient_id
    private PatientEntity patient;
}
