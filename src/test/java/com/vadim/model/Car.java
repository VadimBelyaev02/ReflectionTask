package com.vadim.model;

import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Car {

    private String brand;
    private List<Door> doors;
    private Engine engine;
    private Wheel[] wheels;
    private String model;
    private double fromOneToOneHundred;
}
