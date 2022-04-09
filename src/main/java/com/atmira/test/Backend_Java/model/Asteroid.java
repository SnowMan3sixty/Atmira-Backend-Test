package com.atmira.test.Backend_Java.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor @Getter
public class Asteroid {

    private final String name;    
    private final Double diameterInKm;
    private final Double speedKmHour;
    private final String approachDate;
    private final String orbitingBody;
    
    public Asteroid(String name, Double diameterInKm, Double speedKmHour, String approachDate, String orbitingBody) {
        this.name = name;
        this.diameterInKm = diameterInKm;
        this.speedKmHour = speedKmHour;
        this.approachDate = approachDate;
        this.orbitingBody = orbitingBody;
	}   

    public String getName() {
        return name;
    }
    
    public Double getDiameterInKm() {
        return diameterInKm;
    }
    
    public Double getSpeedKmHour() {
        return speedKmHour;
    }
    
    public String getApproachDate() {
        return approachDate;
    }
    
    public String getOrbitingBody() {
        return orbitingBody;
    }
}
