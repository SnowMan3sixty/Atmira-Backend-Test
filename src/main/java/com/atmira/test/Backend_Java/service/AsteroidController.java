package com.atmira.test.Backend_Java.service;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import com.atmira.test.Backend_Java.model.Asteroid;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class AsteroidController {

    @GetMapping("/asteroids")
    public Object[] potentiallyHazardousAsteroidsTop(@RequestParam(value="days") int days){
        Asteroid[] topHazardAsteroids = new Asteroid[3];
        topHazardAsteroids[0] = new Asteroid("asteroidName",1,2,new Date(), "planet");
        topHazardAsteroids[1] =  new Asteroid("asteroidName2",11,22,new Date(), "planet2");
        return topHazardAsteroids;
    } 

   
}

    

