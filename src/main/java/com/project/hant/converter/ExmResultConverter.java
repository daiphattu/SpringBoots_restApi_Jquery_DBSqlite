package com.project.hant.converter;

import com.project.hant.dtos.ExmResultDTO;
import com.project.hant.entity.ExmResultEntity;
import org.springframework.stereotype.Component;

@Component
public class ExmResultConverter {
    public ExmResultEntity toEntity(ExmResultDTO dto) {
        ExmResultEntity entity = new ExmResultEntity();

        entity.setLeftEye(dto.getLeftEye());
        entity.setRightEye(dto.getRightEye());
        entity.setNote(dto.getNote());

        return entity;
    }

    public ExmResultDTO toDTO(ExmResultEntity entity){
        ExmResultDTO dto = new ExmResultDTO();

        dto.setId(entity.getId());
        dto.setCreatedBy(entity.getCreatedBy());
        dto.setCreatedDate(entity.getCreatedDate());
        dto.setDeleteStatus(entity.getDeleteStatus());
        dto.setModifiedBy(entity.getModifiedBy());
        dto.setModifiedDate(entity.getModifiedDate());
        dto.setStatus(entity.getStatus());

        dto.setLeftEye(entity.getLeftEye());
        dto.setRightEye(entity.getRightEye());
        dto.setNote(entity.getNote());
        dto.setPatient(entity.getPatient());
        return dto;
    }
}
