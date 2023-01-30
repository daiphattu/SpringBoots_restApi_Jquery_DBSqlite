package com.project.hant.controller.apis;

import com.project.hant.service.PatientService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.project.hant.dao.PatientDTO;

import java.util.List;

@Slf4j
@RestController
@CrossOrigin("*")
@RequestMapping("/admin/patient/api")
public class PatientApisMngController {
    @Autowired
    private PatientService patientService;

    @GetMapping("/list")
    public ResponseEntity<?> getAllActivePatient() {
        List<PatientDTO> dtos = patientService.getAllActPatient();
        return new ResponseEntity<>(dtos, HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<?> createPatient(@RequestBody() PatientDTO patientDTO){
        if (patientDTO == null) {
            return new ResponseEntity<>("Cannot create patient", HttpStatus.INTERNAL_SERVER_ERROR);
        }

        PatientDTO dto= patientService.save(patientDTO);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @GetMapping("/get/{ptnId}")
    public ResponseEntity<?> getVersionById(@PathVariable("ptnId") Long ptnId){
        if(ptnId == null){
            return new ResponseEntity<>("Patient not existed!", HttpStatus.INTERNAL_SERVER_ERROR);
        }
        PatientDTO dto = patientService.getById(ptnId);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }
}
