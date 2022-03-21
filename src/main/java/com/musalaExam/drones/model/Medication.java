package com.musalaExam.drones.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "Medication")
public class Medication {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)

    @Column(name="id")
    private Integer id;

    @Column(name = "code")
    private String code;

    @Column(name = "name")
    private String name;

    @Column(name = "weight")
    private double weight;

    @Column(name = "image")
    private String image;
}
