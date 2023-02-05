package com.project.hant.service.Impl;

import com.project.hant.converter.PatientConverter;
import com.project.hant.entity.PatientEntity;
import com.project.hant.exceptions.ConflictException;
import com.project.hant.repositories.PatientRepository;
import com.project.hant.service.PatientService;
import com.project.hant.dao.PatientDTO;
import com.project.hant.utils.CommonStatus;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@Transactional
public class PatientServiceImpl implements PatientService {

    @Autowired
    private PatientRepository patientRepository;

    @Autowired
    private PatientConverter patientConverter;

    @Override
    public List<PatientDTO> getAllActPatient() {
        List<PatientEntity> entity = patientRepository.findAllActivePatient();
        return entity.stream().map(p -> patientConverter.toDTO(p)).collect(Collectors.toList());
    }

    @Override
    public PatientDTO save(PatientDTO patientDTO) {
        if(patientDTO == null) {
            throw new ConflictException("Version is not existed!");
        }
        PatientDTO result = null;
        PatientEntity patientEntity = patientConverter.toEntity(patientDTO);
        if(patientDTO.getId() != null) {
            //update patient
            PatientEntity entity = patientRepository.getPtnById(patientDTO.getId());
            patientEntity.setId(entity.getId());

            patientEntity.setStatus(CommonStatus.WORKING.getIntStatus());
            patientEntity.setDeleteStatus(CommonStatus.NOT_DELETED.getIntStatus());

            result = patientConverter.toDTO(patientRepository.save(patientEntity));
        }else {
            //create new patient
            patientEntity.setStatus(CommonStatus.WORKING.getIntStatus());
            patientEntity.setDeleteStatus(CommonStatus.NOT_DELETED.getIntStatus());

            result = patientConverter.toDTO(patientRepository.save(patientEntity));
        }
        return result;
    }

    @Override
    public PatientDTO getById(Long ptnId) {
        if(ptnId == null) {
            throw new ConflictException("Patient is not existed!");
        }
        PatientEntity entity = patientRepository.getPtnById(ptnId);
        return patientConverter.toDTO(entity);
    }

    @Override
    public PatientDTO deleteById(Long ptnId) {
        if(ptnId == null) {
            throw new ConflictException("patient is not existed!");
        }
        PatientEntity entity = patientRepository.getPtnById(ptnId);

        entity.setStatus(CommonStatus.NOT_WORKING.getIntStatus());
        entity.setDeleteStatus(CommonStatus.DELETED.getIntStatus());
        return patientConverter.toDTO(patientRepository.save(entity));
    }
}
