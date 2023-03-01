package com.project.hant.converter;

import com.project.hant.dtos.MedicineDTO;
import com.project.hant.entity.MedicineEntity;
import org.springframework.stereotype.Component;

@Component
public class MedicineConverter {
    public MedicineEntity toEntity(MedicineDTO dto) {
        MedicineEntity entity = new MedicineEntity();

        entity.setNameOfMedicine(dto.getNameOfMedicine());
        entity.setEffectOfMedicine(dto.getEffectOfMedicine());
        entity.setPriceOfMedicine(dto.getPriceOfMedicine());
        entity.setQuantity(dto.getQuantity());
        entity.setProducer(dto.getProducer());
        return entity;
    }

    public MedicineDTO toDTO(MedicineEntity entity){
        MedicineDTO dao = new MedicineDTO();

        dao.setId(entity.getId());
        dao.setCreatedBy(entity.getCreatedBy());
        dao.setCreatedDate(entity.getCreatedDate());
        dao.setDeleteStatus(entity.getDeleteStatus());
        dao.setModifiedBy(entity.getModifiedBy());
        dao.setModifiedDate(entity.getModifiedDate());
        dao.setStatus(entity.getStatus());

        dao.setNameOfMedicine(entity.getNameOfMedicine());
        dao.setEffectOfMedicine(entity.getEffectOfMedicine());
        dao.setPriceOfMedicine(entity.getPriceOfMedicine());
        dao.setQuantity(entity.getQuantity());
        dao.setProducer(entity.getProducer());
        return dao;
    }
}
