package com.vadim.test;

import com.vadim.model.Car;
import com.vadim.model.Door;
import com.vadim.model.Engine;
import com.vadim.model.Wheel;
import com.vadim.utils.JsonMapper;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class JsonMapperTest {


    @Test
    public void shouldMapWheelToJson() {
        String expectedJson = "{\"size\": 4,\"material\": \"material\"}";
        Wheel wheel = new Wheel(4, "material");

        String json = JsonMapper.toJson(wheel);
        assertEquals(expectedJson, json);
    }

    @Test
    public void shouldMapEngineToJson() {
        String expectedJson = "{\"horsepower\": 1000,\"volume\": 123456.789,\"size\": 10,\"symbol\": \"X\"}";
        Engine engine = new Engine(1000, 123456.789, 10, 'X');

        String json = JsonMapper.toJson(engine);
        assertEquals(expectedJson, json);
    }

    @Test
    public void shouldMapDoorToJson() {
        final String expectedJson = "{\"maker\": \"Maker\",\"width\": 1.5,\"isOpen\": true,\"isElectric\": false}";
        Door door = new Door("Maker", 1.5F, true, false);

        String json = JsonMapper.toJson(door);
        assertEquals(expectedJson, json);
    }

    @Test
    public void shouldMapCarToJson() {
        final String expectedJson = "{\"brand\": \"Жигуль\",\"doors\": [{\"maker\": \"Maker\",\"width\": 1.5,\"isOpen\": true,\"isElectric\": false}, {\"maker\": \"Not a maker\",\"width\": 3.5,\"isOpen\": false,\"isElectric\": true}],\"engine\": {\"horsepower\": 2345235,\"volume\": 3459.1,\"size\": -120,\"symbol\": \"O\"},\"wheels\": [{\"size\": 100,\"material\": \"Metal\"}, {\"size\": -10,\"material\": \"Graphite\"}, {\"size\": 0,\"material\": \"Glass\"}],\"model\": \"NEW\",\"fromOneToOneHundred\": 1.01}";

        Engine engine = new Engine(2345235, 3459.1, -120, 'O');

        Door door1 = new Door("Maker", 1.5, true, false);
        Door door2 = new Door("Not a maker", 3.5, false, true);

        Wheel wheel1 = new Wheel(100, "Metal");
        Wheel wheel2 = new Wheel(-10, "Graphite");
        Wheel wheel3 = new Wheel(0, "Glass");

        List<Door> doors = Stream.of(door1, door2).toList();
        Wheel[] wheels = new Wheel[]{wheel1, wheel2, wheel3};
        Car car = new Car("Жигуль", doors, engine, wheels, "NEW", 1.01);


        String json = JsonMapper.toJson(car);
        assertEquals(expectedJson, json);
    }

    @Test
    public void shouldMapJsonToDoor() {
        final String json = "{\"maker\": \"Maker\",\"width\": 1.5,\"isOpen\": true,\"isElectric\": false}";
        Door expectedDoor = new Door("Maker", 1.5, true, false);

        Door door = JsonMapper.toModel(json, Door.class);
        assertEquals(expectedDoor, door);
    }

    @Test
    public void shouldMapJsonToEngine() {
        final String json = "{\"horsepower\": 1000,\"volume\": 123456.789,\"size\": 10,\"symbol\": \"X\"}";
        Engine expectedEngine = new Engine(1000, 123456.789, 10, 'X');

        Engine engine = JsonMapper.toModel(json, Engine.class);
        assertEquals(expectedEngine, engine);
    }

    @Test
    public void shouldMapJsonToWheel() {
        String json = "{\"size\": 4,\"material\": \"material\"}\n";
        Wheel expectedWheel = new Wheel(4, "material");

        Wheel wheel = JsonMapper.toModel(json, Wheel.class);
        assertEquals(expectedWheel, wheel);
    }

    @Test
    public void shouldMapJsonToCar() {
        final String json = "{\"brand\": \"Жигуль\",\"doors\": [{\"maker\": \"Maker\",\"width\": 1.5,\"isOpen\": true,\"isElectric\": false}, {\"maker\": \"Not a maker\",\"width\": 3.1,\"isOpen\": false,\"isElectric\": true}],\"engine\": {\"horsepower\": 2345235,\"volume\": 3459.01,\"size\": -120,\"symbol\": \"O\"},\"wheels\": [{\"size\": 100,\"material\": \"Metal\"}, {\"size\": -10,\"material\": \"Graphite\"}, {\"size\": 0,\"material\": \"Glass\"}],\"model\": \"NEW\",\"fromOneToOneHundred\": 1.01}";

        Engine engine = new Engine(2345235, 3459.01, 120, 'O');

        Door door1 = new Door("Maker", 1.5F, true, false);
        Door door2 = new Door("Not a maker", 3.1F, false, true);

        Wheel wheel1 = new Wheel(100, "Metal");
        Wheel wheel2 = new Wheel(-10, "Graphite");
        Wheel wheel3 = new Wheel(0, "Glass");

        List<Door> doors = Stream.of(door1, door2).toList();
        Wheel[] wheels = new Wheel[]{wheel1, wheel2, wheel3};
        Car excpectedCar = new Car("Жигуль", doors, engine, wheels, "NEW", 1.01);

        Car car = JsonMapper.toModel(json, Car.class);
        assertEquals(excpectedCar, car);
    }
}
