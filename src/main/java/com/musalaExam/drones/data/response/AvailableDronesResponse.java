package com.musalaExam.drones.data.response;

import com.musalaExam.drones.model.Drone;
import lombok.Data;

import java.util.List;

@Data
public class AvailableDronesResponse {

    private List<Drone> drones;
}
