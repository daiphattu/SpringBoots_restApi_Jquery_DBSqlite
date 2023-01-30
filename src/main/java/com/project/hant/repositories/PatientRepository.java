package com.project.hant.repositories;

import com.project.hant.entity.PatientEntity;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Transactional
public interface PatientRepository extends JpaRepository<PatientEntity,Long> {
    @Query(nativeQuery = true, value = "select * from patient where delete_status = 1")
    List<PatientEntity> findAllActivePatient();
    @Query(nativeQuery = true, value = "select * from patient where delete_status = 1 and id = :ptnId")
    PatientEntity getPtnById(@Param("ptnId") Long ptnId);
}
