package com.vadim.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
public class Car {

    private String brand;
    private List<Door> doors;
    private Engine engine;
    private Wheel[] wheels;
    private String model;
    private double fromOneToOneHundred;

}
