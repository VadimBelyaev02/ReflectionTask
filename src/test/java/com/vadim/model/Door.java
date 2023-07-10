package com.vadim.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
public class Door {

    private String maker;
    private double width;
    private boolean isOpen;
    private Boolean isElectric;

    public Door() {
    }
}
