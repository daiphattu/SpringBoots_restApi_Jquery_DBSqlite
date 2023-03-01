package com.project.hant.repositories;

import com.project.hant.entity.MedicineEntity;
import com.project.hant.entity.PatientEntity;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Transactional
public interface MedicineRepository extends JpaRepository<MedicineEntity,Long> {
    @Query(nativeQuery = true, value = "select * from medicine where delete_status = 1")
    List<MedicineEntity> findAllActiveMedicine();

    @Query(nativeQuery = true, value = "select * from medicine where delete_status = 1 and id = :mdnId")
    MedicineEntity getMdnById(@Param("mdnId") Long mdnId);

    @Query(nativeQuery = true, value = "select * from medicine where delete_status = 1 and id = :mdcId")
    MedicineEntity getMdcById(@Param("mdcId") Long mdcId);
}
