package com.musalaExam.drones.model;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "load_medication")
public class LoadMedication {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Integer id;

    @Column(name = "source_location", columnDefinition = "VARCHAR(255) NOT NULL")
    private String sourceLocation;

    @Column(name = "destination_location", columnDefinition = "VARCHAR(255) NOT NULL")
    private String destinationLocation;

    @Column(name = "load_date", columnDefinition = "VARCHAR(255) ")
    private LocalDateTime loadDate;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "fk_serial_no", referencedColumnName = "serial_number")
    private Drone drone;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "fk_medication_id", referencedColumnName = "id" , unique = true)
    private Medication medication;

}
