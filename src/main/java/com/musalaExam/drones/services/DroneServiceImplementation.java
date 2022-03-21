package com.musalaExam.drones.services;

import com.musalaExam.drones.data.request.DroneRequest;
import com.musalaExam.drones.data.request.LoadMedicationRequest;
import com.musalaExam.drones.data.response.BatteryResponse;
import com.musalaExam.drones.data.response.DroneResponse;
import com.musalaExam.drones.data.response.LoadMedicationResponse;
import com.musalaExam.drones.data.response.MedicationLoadedResponse;
import com.musalaExam.drones.enums.ModelEnum;
import com.musalaExam.drones.enums.StatusEnum;
import com.musalaExam.drones.model.Drone;
import com.musalaExam.drones.model.LoadMedication;
import com.musalaExam.drones.model.Medication;
import com.musalaExam.drones.repositories.DroneRepository;
import com.musalaExam.drones.repositories.LoadMedicationRepository;
import com.musalaExam.drones.repositories.MedicationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.time.LocalDateTime;
import java.util.List;


@Service
public class DroneServiceImplementation implements DroneService {

    @Autowired
    private DroneRepository droneRepository;
    @Autowired
    private MedicationRepository medicationRepository;
    @Autowired
    private LoadMedicationRepository loadMedicationRepository;

    @Override
    public DroneResponse register(DroneRequest droneRequest) {
        Drone drone = new Drone();
        drone.setSerialNumber(droneRequest.getSerialNumber());
        drone.setModel(ModelEnum.valueOf(droneRequest.getModel()).name());
        drone.setBatteryCapacity(droneRequest.getBattery());
        drone.setWeightLimit(droneRequest.getWeightLimit());
        drone.setState(StatusEnum.valueOf(droneRequest.getState()).name());

        droneRepository.save(drone);

        DroneResponse droneResponse = new DroneResponse();
        droneResponse.setMessage("drone saved successfully");
        droneResponse.setSerialNumber(drone.getSerialNumber());

        return droneResponse;
    }

    @Override
    public List<Drone> getAllAvailableDrones() {

        return droneRepository.findAllByState(StatusEnum.IDLE.name());
    }

    @Override
    public BatteryResponse getBatteryLevel(String serialNumber) {
        if (!droneRepository.findBySerialNumber(serialNumber).isPresent()) {
            throw new RuntimeException("invalid drone ");
        }

        Drone drone = droneRepository.findBySerialNumber(serialNumber).get();
        DecimalFormat format = new DecimalFormat("#%");

        BatteryResponse batteryResponse = new BatteryResponse();
        batteryResponse.setSerialNumber(drone.getSerialNumber());
        batteryResponse.setBattery(format.format(drone.getBatteryCapacity()));

        return batteryResponse;
    }

    @Override
    public LoadMedicationResponse loadMedication(LoadMedicationRequest loadMedicationRequest) {

        if (!droneRepository.findBySerialNumber(loadMedicationRequest.getSerialNumber()).isPresent())
            throw new RuntimeException("invalid Drone serial no");
        if (!medicationRepository.findByCode(loadMedicationRequest.getCode()).isPresent())
            throw new RuntimeException("invalid Medication Code");

        Drone drone = droneRepository.findBySerialNumber(loadMedicationRequest.getSerialNumber()).get();
        Medication medication = medicationRepository.findByCode(loadMedicationRequest.getCode()).get();

        if (drone.getWeightLimit() < medication.getWeight()) {
            throw new RuntimeException("The medication weight exceeds the weight limit of a drone");
        }

        if (drone.getBatteryCapacity().compareTo(new BigDecimal(0.25)) < 0) {
            throw new RuntimeException("Drone can't load medication while battery capacity is  below 25%");
        }
        //lets load the medication on loadMedication table

        LoadMedication loadMedication = new LoadMedication();
        loadMedication.setDrone(drone);
        loadMedication.setMedication(medication);
        loadMedication.setSourceLocation(loadMedicationRequest.getSourceLocation());
        loadMedication.setDestinationLocation(loadMedicationRequest.getDestinationLocation());
//        loadMedication.getLoadDate(new LocalDateTime());
        loadMedicationRepository.save(loadMedication);
        // after its saved this means its loaded state for the drone
        droneRepository.setUpdateState("LOADED", loadMedicationRequest.getSerialNumber());
        LoadMedicationResponse loadMedicationResponse = new LoadMedicationResponse();
        loadMedicationResponse.setSerialNumber(loadMedicationRequest.getSerialNumber());
        loadMedicationResponse.setCode(loadMedicationRequest.getCode());
        loadMedicationResponse.setMessage("Medication with code " + loadMedicationRequest.getCode() + " loaded successfully on drone with serial " + loadMedicationRequest.getSerialNumber());

        return loadMedicationResponse;
    }

    @Override
    public MedicationLoadedResponse checkLoadedMedicationForDrone(String serialNumber) {
        LoadMedication loadMedication = loadMedicationRepository.findByDrone(serialNumber);
        if (loadMedication == null)
            throw new RuntimeException("this drone didn't load any medication");
        MedicationLoadedResponse medicationLoadedResponse = new MedicationLoadedResponse();
        medicationLoadedResponse.setSerialNumber(serialNumber);
        medicationLoadedResponse.setMedication(loadMedication.getMedication());
        medicationLoadedResponse.setMessage("success");


         return medicationLoadedResponse;
    }

}
