package com.musalaExam.drones.repositories;

import com.musalaExam.drones.enums.StatusEnum;
import com.musalaExam.drones.model.Drone;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Transactional
public interface DroneRepository extends JpaRepository<Drone,String> {


   List<Drone> findAllByState(String state);
   Optional<Drone> findBySerialNumber(String serialNumberl);


   @Modifying
   @Query(value = "update Drone d set d.state = :state where  d.serialNumber= :serialno")
   void setUpdateState (@Param("state") String status, @Param("serialno") String serialNo);


}
