package com.vadim.test;

import com.vadim.model.Engine;
import com.vadim.model.Wheel;
import com.vadim.utils.JsonMapper;
import org.junit.jupiter.api.Test;

public class JsonMapperTest {

    @Test
    public void shouldMapWheelToJson() {
        Wheel wheel = Wheel.builder()
                .size(4)
                .material("material")
                .build();

        String json = JsonMapper.toJson(wheel);
        System.out.println(json);
    }

    @Test
    public void shouldMapEngineToJson() {

        Engine engine = Engine.builder()
                .horsepower(1000)
                .volume(123456.789)
                .manufacturer(new char[]{'T', 'e', 's', 't', 'i', 'n', 'g'})
                .symbol('X')
                .size((byte) 10)
                .build();

        String json = JsonMapper.toJson(engine);
        System.out.println(json);
    }
}
