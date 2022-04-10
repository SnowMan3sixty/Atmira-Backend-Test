package com.atmira.test.Backend_Java.service;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.atmira.test.Backend_Java.model.Asteroid;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class AsteroidControllerTest {

    @Autowired
    AsteroidController asteroidController = new AsteroidController();
    
    ObjectMapper mapper = new ObjectMapper();

    @Test
    void testCalculateAsteroidDiameter() throws JsonMappingException, JsonProcessingException {        
        JsonNode sampleNode = mapper.readTree("{\"estimated_diameter_min\": 0.029,\"estimated_diameter_max\": 0.065}");
        Double diameter = asteroidController.calculateAsteroidDiameter(sampleNode);

        Assertions.assertEquals(0.047,diameter);
    }

    @Test
    void testSortAsteroidsByDiameter() {
        List<Asteroid> asteroidList = new ArrayList<Asteroid>();
                      
        asteroidList.add(new Asteroid("sampleAsteroid1", 10.0, 10.0, "date1", "orbitingBody"));
        asteroidList.add(new Asteroid("sampleAsteroid1", 30.0, 10.0, "date1", "orbitingBody"));
        asteroidList.add(new Asteroid("sampleAsteroid1", 20.0, 10.0, "date1", "orbitingBody"));

        asteroidList = asteroidController.sortAsteroidsByDiamter(asteroidList);

        Assertions.assertEquals(30.0,asteroidList.get(0).getDiameterInKm());
        Assertions.assertEquals(20.0,asteroidList.get(1).getDiameterInKm());
        Assertions.assertEquals(10.0,asteroidList.get(2).getDiameterInKm());

    }

    @Test
    void testGenerateHazardousAsteroidList() throws IOException{
        JsonNode sampleNode = mapper.readTree(new File("src\\test\\resources\\asteroidJsonSample.json"));

        List<Asteroid> asteroidList = asteroidController.generateHazardousAsteroidList(sampleNode);

        Assertions.assertEquals("\"349507 (2008 QY)\"",asteroidList.get(0).getName());
        Assertions.assertEquals("\"363599 (2004 FG11)\"",asteroidList.get(1).getName());
        Assertions.assertEquals("\"(2011 GM44)\"",asteroidList.get(2).getName());
        
    }

}
