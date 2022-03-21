package com.musalaExam.drones.model;

import com.musalaExam.drones.enums.ModelEnum;
import com.musalaExam.drones.enums.StatusEnum;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;

@Data
@Entity
@Table(name = "Drone")
public class Drone {


    @Id
    @Column(length = 100,name="serial_number")
    private String serialNumber;

    @Column(name = "model")
    private String model;

    @Column(name = "weight_limit")
    private double weightLimit;


    @Column(name = "battery_capacity",precision = 3, scale = 2)
    private BigDecimal batteryCapacity;

    @Column(name = "state")
    private String state;






}
