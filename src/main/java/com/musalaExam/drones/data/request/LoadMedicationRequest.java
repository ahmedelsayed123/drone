package com.musalaExam.drones.data.request;

import lombok.Data;

@Data
public class LoadMedicationRequest {

    private String serialNumber;
    private String sourceLocation;
    private String destinationLocation;
    private String code;

}
