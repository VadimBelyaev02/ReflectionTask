package com.vadim.test;

import com.vadim.model.Car;
import com.vadim.model.Door;
import com.vadim.model.Engine;
import com.vadim.model.Wheel;
import com.vadim.utils.JsonMapper;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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
                .symbol('X')
                .size((byte) 10)
                .build();

        String json = JsonMapper.toJson(engine);
        System.out.println(json);
    }

    @Test
    public void shouldMapDoorToJson() {
        Door door = Door.builder()
                .maker("Maker")
                .isOpen(true)
                .isElectric(false)
                .width(1.5F)
                .build();

        String json = JsonMapper.toJson(door);
        System.out.println(json);
    }

    @Test
    public void shouldMapCarToJson() {
        Door door1 = Door.builder()
                .maker("Maker")
                .isOpen(true)
                .isElectric(false)
                .width(1.5F)
                .build();
        Door door2 = Door.builder()
                .maker("Not a maker")
                .isOpen(false)
                .isElectric(true)
                .width(3.1F)
                .build();

        Wheel wheel1 = Wheel.builder()
                .material("Metal")
                .size(100)
                .build();

        Wheel wheel2 = Wheel.builder()
                .material("Graphite")
                .size(-10)
                .build();

        Wheel wheel3 = Wheel.builder()
                .material("Glass")
                .size(0)
                .build();

        List<Door> doors = Stream.of(door1, door2).toList();

        Wheel[] wheels = new Wheel[]{wheel1, wheel2, wheel3};

        Engine engine = Engine.builder()
                .volume(3459.01)
                .size((byte) -120)
                .symbol('O')
                .horsepower(2345235)
                .build();

        Car car = Car.builder()
                .brand("Жигуль")
                .model("NEW")
                .doors(doors)
                .wheels(wheels)
                .engine(engine)
                .fromOneToOneHundred(1.01)
                .build();

        String json = JsonMapper.toJson(car);
        System.out.println(json);
    }
}
