package org.example;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;
import org.junit.jupiter.params.provider.NullSource;

import java.security.spec.RSAOtherPrimeInfo;

import static org.junit.jupiter.api.Assertions.*;

class TrafficLightEnumTest {

    @Test
    void demoSimple(){
        TrafficLightEnum trafficLight = TrafficLightEnum.RED;
        System.out.println("Name: " + trafficLight.name());
        System.out.println("Ordinal: " + trafficLight.ordinal());
    }

    @Test
    void demoSimple2(){
        for (TrafficLightEnum trafficLight: TrafficLightEnum.values()) {
            System.out.println("ToString: " + trafficLight);
            System.out.println("Name: " + trafficLight.name());
            System.out.println("Ordinal: " + trafficLight.ordinal());
            System.out.println("Duration: " + trafficLight.getDuration());
            trafficLight.dummy();
        }
    }

    @ParameterizedTest
    @EnumSource(TrafficLightEnum.class)
    @NullSource
    void demoSimple3(TrafficLightEnum trafficLight){
        System.out.println("ToString: " + trafficLight);
        System.out.println("Name: " + trafficLight.name());
        System.out.println("Ordinal: " + trafficLight.ordinal());
        System.out.println("Duration: " + trafficLight.getDuration());
        trafficLight.dummy();
    }

    @Test
    void demoParse(){
        TrafficLightEnum trafficLight = TrafficLightEnum.valueOf("RED");
        Assertions.assertEquals(TrafficLightEnum.RED, trafficLight);
    }


}