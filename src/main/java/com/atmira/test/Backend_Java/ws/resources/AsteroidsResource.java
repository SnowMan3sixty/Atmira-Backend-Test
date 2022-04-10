package com.atmira.test.Backend_Java.ws.resources;

import java.util.List;

import com.atmira.test.Backend_Java.exception.InvalidParameterException;
import com.atmira.test.Backend_Java.model.Asteroid;
import com.atmira.test.Backend_Java.service.AsteroidController;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AsteroidsResource {
    @GetMapping("/asteroids")
    public List<Asteroid> potentiallyHazardousAsteroidsTop(@RequestParam(value="days") int days){        
        if(days < 1 || days > 7){
            throw new InvalidParameterException("The day parameter must be between 1 and 7. Your parameter: " + days);
        }
        AsteroidController asteroidController = new AsteroidController();        
        List<Asteroid> asteroidList = asteroidController.getPotentiallyHazardousAsteroidsTop3(days);
       
        return asteroidList;
    } 
}
