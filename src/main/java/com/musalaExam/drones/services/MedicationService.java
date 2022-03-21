package com.musalaExam.drones.services;

import com.musalaExam.drones.data.request.MedicationRequest;
import com.musalaExam.drones.data.response.MedicationResponse;
import org.springframework.stereotype.Component;

@Component
public interface MedicationService {


    MedicationResponse addMedication(MedicationRequest medicationRequest);
}
