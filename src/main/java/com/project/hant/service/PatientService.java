package com.project.hant.service;

import java.util.List;
import com.project.hant.dao.PatientDTO;

public interface PatientService {
    List<PatientDTO> getAllActPatient();

    PatientDTO save(PatientDTO patientDTO);

    PatientDTO getById(Long ptnId);

    PatientDTO deleteById(Long ptnId);
}
