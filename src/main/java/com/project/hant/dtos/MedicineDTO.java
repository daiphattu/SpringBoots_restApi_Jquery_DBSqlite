package com.project.hant.dtos;


import com.project.hant.entity.BaseEntity;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class MedicineDTO extends BaseEntity {
    private String nameOfMedicine;

    private String effectOfMedicine;

    private String priceOfMedicine;

    private String quantity;

    private String producer;
}
