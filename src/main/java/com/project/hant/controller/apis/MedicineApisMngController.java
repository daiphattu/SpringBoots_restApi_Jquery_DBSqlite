package com.project.hant.controller.apis;

import com.project.hant.dtos.MedicineDTO;
import com.project.hant.dtos.PatientDTO;
import com.project.hant.service.MedicineService;
import com.project.hant.service.PatientService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@CrossOrigin("*")
@RequestMapping("/admin/medicine/api")
public class MedicineApisMngController {
    @Autowired
    private MedicineService medicineService;

    @GetMapping("/list")
    public ResponseEntity<?> getAllActiveMedicine() {
        List<MedicineDTO> dtos = medicineService.getAllActMedicine();
        return new ResponseEntity<>(dtos, HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<?> createMedicine(@RequestBody() MedicineDTO medicineDTO){
        if (medicineDTO == null) {
            return new ResponseEntity<>("Cannot create medicine", HttpStatus.INTERNAL_SERVER_ERROR);
        }

        MedicineDTO dto= medicineService.save(medicineDTO);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @GetMapping("/get/{mdcId}")
    public ResponseEntity<?> getMedicineById(@PathVariable("mdcId") Long mdcId){
        if(mdcId == null){
            return new ResponseEntity<>("Medicine not existed!", HttpStatus.INTERNAL_SERVER_ERROR);
        }
        MedicineDTO dto = medicineService.getById(mdcId);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @PostMapping("/update/{id}")
    public ResponseEntity<?> updateMedicine(@PathVariable(value = "id") Long mdcId,@RequestBody() MedicineDTO medicineDTO){
        if (medicineDTO == null) {
            return new ResponseEntity<>("Cannot create medicine", HttpStatus.INTERNAL_SERVER_ERROR);
        }
        medicineDTO.setId(mdcId);
        MedicineDTO dto= medicineService.save(medicineDTO);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{mdcId}")
    public ResponseEntity<?> deleteMedicine(@PathVariable("mdcId") Long mdcId){
        if(mdcId == null){
            return new ResponseEntity<>("Medicine not existed!", HttpStatus.INTERNAL_SERVER_ERROR);
        }
        MedicineDTO dto = medicineService.deleteById(mdcId);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }
}
