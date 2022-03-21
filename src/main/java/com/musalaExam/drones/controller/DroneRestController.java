package com.musalaExam.drones.controller;


import com.musalaExam.drones.data.request.DroneRequest;
import com.musalaExam.drones.data.request.LoadMedicationRequest;
import com.musalaExam.drones.data.request.MedicationRequest;
import com.musalaExam.drones.data.response.*;
import com.musalaExam.drones.model.Drone;
import com.musalaExam.drones.model.LoadMedication;
import com.musalaExam.drones.services.DroneService;
import com.musalaExam.drones.services.MedicationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping(path="/musala/drone")
public class DroneRestController {

    @Autowired
    private DroneService droneService;

    @Autowired
    private MedicationService medicationService;


    @PostMapping(path="/register", consumes = "application/json", produces = "application/json")
    public ResponseEntity<DroneResponse> registerDrone(@RequestBody DroneRequest droneRequest){

     DroneResponse droneResponse = droneService.register(droneRequest);
     return new ResponseEntity<DroneResponse>(droneResponse, HttpStatus.OK);

    }
    @GetMapping(path= "/availableDrones", produces = "application/json")
    public ResponseEntity<AvailableDronesResponse> getAvailableDroneForLoading() {
        List<Drone> drones = droneService.getAllAvailableDrones();
        AvailableDronesResponse availableDronesResponse = new AvailableDronesResponse();
        availableDronesResponse.setDrones(drones);
        return new ResponseEntity<AvailableDronesResponse>(availableDronesResponse, HttpStatus.OK);
    }

    @GetMapping(path ="/checkBatteryLevel", produces = "application/json")
    public ResponseEntity<BatteryResponse> checkBatteryLevel(
            @RequestParam(required = true, name = "serialNumber") String serialNumber) {
        if ( serialNumber.isEmpty()) {
            throw new RuntimeException("SerialNumber cannot be empty");
        }
        BatteryResponse batteryResponse = droneService.getBatteryLevel(serialNumber);
        return new ResponseEntity<BatteryResponse>(batteryResponse, HttpStatus.OK);
    }
    //load medication to drone
    @PostMapping(path = "/loadMedication", consumes = "application/json", produces = "application/json")
    public ResponseEntity<LoadMedicationResponse> loadMedication(@RequestBody LoadMedicationRequest loadMedicationRequest) {
        LoadMedicationResponse loadMedicationResponse = droneService.loadMedication(loadMedicationRequest);
        return new ResponseEntity<LoadMedicationResponse>(loadMedicationResponse, HttpStatus.OK);
    }


    //add medication
    @PostMapping(path="/addMedication", consumes = "application/json", produces = "application/json")
    public ResponseEntity<MedicationResponse> addMedication(@RequestBody MedicationRequest medicationRequest){

        MedicationResponse medicationResponse = medicationService.addMedication(medicationRequest);
        return new ResponseEntity<MedicationResponse>(medicationResponse, HttpStatus.OK);

    }
    @GetMapping(path ="/checkLoadedMedicationForDrone", produces = "application/json")
    public ResponseEntity<MedicationLoadedResponse> checkLoadedMedicationForDrone(
            @RequestParam(required = true, name = "serialNumber") String serialNumber) {
        if ( serialNumber.isEmpty()) {
            throw new RuntimeException("SerialNumber cannot be empty");
        }
        MedicationLoadedResponse medicationLoadedResponse = droneService.checkLoadedMedicationForDrone(serialNumber);
        return new ResponseEntity<MedicationLoadedResponse>(medicationLoadedResponse, HttpStatus.OK);
    }
}
