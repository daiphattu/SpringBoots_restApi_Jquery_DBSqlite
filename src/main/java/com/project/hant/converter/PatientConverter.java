package com.project.hant.converter;

import com.project.hant.entity.PatientEntity;
import org.springframework.stereotype.Component;
import com.project.hant.dtos.PatientDTO;

@Component
public class PatientConverter {
    public PatientEntity toEntity(PatientDTO dto) {
        PatientEntity entity = new PatientEntity();

        entity.setNamePatient(dto.getNamePatient());
        entity.setBirthdayPatient(dto.getBirthdayPatient());
        entity.setGenderPatient(dto.getGenderPatient());
        entity.setAddressPatient(dto.getAddressPatient());
        entity.setIdCard(dto.getIdCard());
        entity.setPhonePatient(dto.getPhonePatient());

        return entity;
    }

    public PatientDTO toDTO(PatientEntity entity){
        PatientDTO dao = new PatientDTO();

        dao.setId(entity.getId());
        dao.setCreatedBy(entity.getCreatedBy());
        dao.setCreatedDate(entity.getCreatedDate());
        dao.setDeleteStatus(entity.getDeleteStatus());
        dao.setModifiedBy(entity.getModifiedBy());
        dao.setModifiedDate(entity.getModifiedDate());
        dao.setStatus(entity.getStatus());

        dao.setNamePatient(entity.getNamePatient());
        dao.setBirthdayPatient(entity.getBirthdayPatient());
        dao.setGenderPatient(entity.getGenderPatient());
        dao.setAddressPatient(entity.getAddressPatient());
        dao.setIdCard(entity.getIdCard());
        dao.setPhonePatient(entity.getPhonePatient());

        return dao;
    }
}
