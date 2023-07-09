package com.vadim.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Wheel {

    private int size;
    private String material;
}
