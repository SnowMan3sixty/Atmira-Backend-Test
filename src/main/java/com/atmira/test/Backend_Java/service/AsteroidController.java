package com.atmira.test.Backend_Java.service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import com.atmira.test.Backend_Java.model.Asteroid;
import com.fasterxml.jackson.databind.JsonNode;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class AsteroidController {

    private final String NASA_URL = "https://api.nasa.gov/neo/rest/v1/feed?start_date=%s&end_date=%s-12-12&api_key=%s";
    private final String API_KEY = "DEMO_KEY";
    //Use this other api key if the demo is not working
    // private final String API_KEY = "zdUP8ElJv1cehFM0rsZVSQN7uBVxlDnu4diHlLSb";

    public List<Asteroid> getPotentiallyHazardousAsteroidsTop3(int dayLapse){

        JsonNode nasaApiResponse = callNasaApi(dayLapse);
        List<Asteroid> hazardousAsteroidList = generateHazardousAsteroidList(nasaApiResponse);
       
        hazardousAsteroidList = sortAsteroidsByDiamter(hazardousAsteroidList);     

        if(hazardousAsteroidList.size() > 3 ){
            hazardousAsteroidList = hazardousAsteroidList.subList(0,3);
        }

        return hazardousAsteroidList;
    }

    private JsonNode callNasaApi(int daysLapse){
        RestTemplate restTemplate = new RestTemplate();
        
        String pattern = "yyyy-MM-dd";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
        Date currentDate = new Date();
        String currentDateString = simpleDateFormat.format(currentDate);

        Calendar c = Calendar.getInstance();
        c.setTime(currentDate);
        c.add(Calendar.DATE, daysLapse);
        String dateLapseString = simpleDateFormat.format(c.getTime());
        
        JsonNode apiResponse = restTemplate.getForObject(String.format(NASA_URL,currentDateString,dateLapseString,API_KEY),JsonNode.class);       
        
        return apiResponse;
    }

    private List<Asteroid> generateHazardousAsteroidList(JsonNode nasaApiResponse){
        List<Asteroid> asteroidList = new ArrayList<Asteroid>();

        for (JsonNode dateNode : nasaApiResponse.path("near_earth_objects")) {
            for (JsonNode asteroidNode : dateNode) {
                if(asteroidNode.get("is_potentially_hazardous_asteroid").asBoolean()){
                    String name = asteroidNode.get("name").toString();
                    Double diameterInKm = calculateAsteroidDiameter(asteroidNode.path("estimated_diameter").path("kilometers"));
                    Double speedKmHour = asteroidNode.path("close_approach_data").get(0).path("relative_velocity").path("kilometers_per_hour").asDouble();
                    String approachDate = asteroidNode.path("close_approach_data").get(0).path("close_approach_date").toString();
                    String orbitingBody = asteroidNode.path("close_approach_data").get(0).path("orbiting_body").toString();
                    asteroidList.add(new Asteroid(name, diameterInKm, speedKmHour, approachDate, orbitingBody));
                }                              
            }
        }

        return asteroidList;
    }
   
    private Double calculateAsteroidDiameter(JsonNode asteroidNode){
        Double minDiameter = asteroidNode.path("estimated_diameter_min").asDouble();
        Double maxDiameter = asteroidNode.path("estimated_diameter_max").asDouble();
        
        return (minDiameter + maxDiameter) / 2;
    }

    private List<Asteroid> sortAsteroidsByDiamter(List<Asteroid> asteroidList){  
        asteroidList.sort((o1, o2)
                      -> o2.getDiameterInKm().compareTo(o1.getDiameterInKm()));
        return asteroidList;
    }
}

    

