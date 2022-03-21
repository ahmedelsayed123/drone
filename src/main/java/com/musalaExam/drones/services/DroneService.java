package com.musalaExam.drones.services;

import com.musalaExam.drones.data.request.DroneRequest;
import com.musalaExam.drones.data.request.LoadMedicationRequest;
import com.musalaExam.drones.data.response.BatteryResponse;
import com.musalaExam.drones.data.response.DroneResponse;
import com.musalaExam.drones.data.response.LoadMedicationResponse;
import com.musalaExam.drones.data.response.MedicationLoadedResponse;
import com.musalaExam.drones.model.Drone;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface DroneService {

    DroneResponse register(DroneRequest drone);
    List<Drone> getAllAvailableDrones();
    BatteryResponse getBatteryLevel(String serialNumber) ;


    LoadMedicationResponse loadMedication(LoadMedicationRequest loadMedicationRequest);
    MedicationLoadedResponse checkLoadedMedicationForDrone(String serialNumber);


}
