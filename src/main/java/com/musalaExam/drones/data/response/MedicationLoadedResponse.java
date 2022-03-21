package com.musalaExam.drones.data.response;

import com.musalaExam.drones.model.Medication;
import lombok.Data;

@Data
public class MedicationLoadedResponse {
    private String serialNumber;
    private Medication medication;
    private String message;
}
