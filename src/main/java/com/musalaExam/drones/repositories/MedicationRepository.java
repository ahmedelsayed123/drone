package com.musalaExam.drones.repositories;

import com.musalaExam.drones.model.Drone;
import com.musalaExam.drones.model.Medication;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MedicationRepository extends JpaRepository<Medication,String> {

    Optional<Medication> findByCode(String code);

}
