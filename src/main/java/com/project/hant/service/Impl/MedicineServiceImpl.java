package com.project.hant.service.Impl;

import com.project.hant.converter.MedicineConverter;
import com.project.hant.converter.PatientConverter;
import com.project.hant.dtos.MedicineDTO;
import com.project.hant.dtos.PatientDTO;
import com.project.hant.entity.MedicineEntity;
import com.project.hant.entity.PatientEntity;
import com.project.hant.exceptions.ConflictException;
import com.project.hant.repositories.MedicineRepository;
import com.project.hant.repositories.PatientRepository;
import com.project.hant.service.MedicineService;
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
public class MedicineServiceImpl implements MedicineService {

    @Autowired
    private MedicineRepository medicineRepository;

    @Autowired
    private MedicineConverter medicineConverter;

    @Override
    public List<MedicineDTO> getAllActMedicine() {
        List<MedicineEntity> entity = medicineRepository.findAllActiveMedicine();
        return entity.stream().map(p -> medicineConverter.toDTO(p)).collect(Collectors.toList());
    }

    @Override
    public MedicineDTO save(MedicineDTO medicineDTO) {
        if(medicineDTO == null) {
            throw new ConflictException("medicine is not existed!");
        }
        MedicineDTO result = null;
        MedicineEntity medicineEntity = medicineConverter.toEntity(medicineDTO);
        if(medicineDTO.getId() != null) {
            //update patient
            MedicineEntity entity = medicineRepository.getMdcById(medicineDTO.getId());
            medicineEntity.setId(entity.getId());

            medicineEntity.setStatus(CommonStatus.WORKING.getIntStatus());
            medicineEntity.setDeleteStatus(CommonStatus.NOT_DELETED.getIntStatus());

            result = medicineConverter.toDTO(medicineRepository.save(medicineEntity));
        }else {
            //create new patient
            medicineEntity.setStatus(CommonStatus.WORKING.getIntStatus());
            medicineEntity.setDeleteStatus(CommonStatus.NOT_DELETED.getIntStatus());

            result = medicineConverter.toDTO(medicineRepository.save(medicineEntity));
        }
        return result;
    }

    @Override
    public MedicineDTO getById(Long mdcId) {
        if(mdcId == null) {
            throw new ConflictException("Medicine is not existed!");
        }
        MedicineEntity entity = medicineRepository.getMdcById(mdcId);
        return medicineConverter.toDTO(entity);
    }

    @Override
    public MedicineDTO deleteById(Long mdcId) {
        if(mdcId == null) {
            throw new ConflictException("Medicine is not existed!");
        }
        MedicineEntity entity = medicineRepository.getMdcById(mdcId);

        entity.setStatus(CommonStatus.NOT_WORKING.getIntStatus());
        entity.setDeleteStatus(CommonStatus.DELETED.getIntStatus());
        return medicineConverter.toDTO(medicineRepository.save(entity));
    }

}
