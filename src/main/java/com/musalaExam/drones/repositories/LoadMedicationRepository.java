package com.musalaExam.drones.repositories;

import com.musalaExam.drones.model.LoadMedication;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface LoadMedicationRepository extends JpaRepository<LoadMedication,Integer> {


    @Query(value = "SELECT * from load_medication e where e.fk_serial_no =:serialNo ", nativeQuery = true)
    LoadMedication findByDrone(@Param("serialNo") String serialNo);


}
