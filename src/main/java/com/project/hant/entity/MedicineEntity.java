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
@Table(name = "medicine")
public class MedicineEntity extends BaseEntity{
    @Column(name = "name_of_medicine", nullable = false)
    private String nameOfMedicine;

    @Column(name = "effect_of_medicine", nullable = false)
    private String effectOfMedicine;

    @Column(name = "price_of_medicine", nullable = false)
    private String priceOfMedicine;

}
