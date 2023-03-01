package com.project.hant.service;


import com.project.hant.dtos.MedicineDTO;

import java.util.List;

public interface MedicineService {
    List<MedicineDTO> getAllActMedicine();

    MedicineDTO save(MedicineDTO medicineDTO);

    MedicineDTO getById(Long mdcId);

    MedicineDTO deleteById(Long mdcId);
}
