package com.musalaExam.drones.services;

import com.musalaExam.drones.data.request.MedicationRequest;
import com.musalaExam.drones.data.response.MedicationResponse;
import com.musalaExam.drones.model.Medication;
import com.musalaExam.drones.repositories.MedicationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MedicationServiceImplementation implements MedicationService {
    @Autowired
    private MedicationRepository medicationRepository;
    @Override
    public MedicationResponse addMedication(MedicationRequest medicationRequest) {
        Medication medication = new Medication();
        medication.setCode(medicationRequest.getCode());
        medication.setName(medicationRequest.getName());
        medication.setWeight(medicationRequest.getWeight());
        medication.setImage(medicationRequest.getImage());
        medicationRepository.save(medication);
        MedicationResponse medicationResponse = new MedicationResponse();
        medicationResponse.setCode(medication.getCode());
        medicationResponse.setMessage("medication added successfully");

        return medicationResponse;
    }
}
