package com.musalaExam.drones.data.request;

import lombok.Data;

import javax.persistence.Column;

@Data
public class MedicationRequest {
    private String code;
    private String name;
    private double weight;
    private String image;
}
