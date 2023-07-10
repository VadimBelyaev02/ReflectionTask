package com.vadim.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Engine {

    private Integer horsepower;
    private Double volume;
    private Integer size;
    private Character symbol;
}
