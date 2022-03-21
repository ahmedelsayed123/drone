package com.musalaExam.drones.data.request;

import com.musalaExam.drones.enums.ModelEnum;
import com.musalaExam.drones.enums.StatusEnum;
import lombok.Data;

import java.math.BigDecimal;
@Data
public class DroneRequest {

    private String serialNumber;
    private String model;
    private double weightLimit;
    private BigDecimal battery;
    private String state;
}
