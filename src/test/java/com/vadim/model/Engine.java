package com.vadim.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Engine {

    private Integer horsepower;
    private Double volume;
    private Byte size;
    private Character symbol;
}
