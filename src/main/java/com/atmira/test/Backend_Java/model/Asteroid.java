package com.atmira.test.Backend_Java.model;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor @Getter
public class Asteroid {

    private final String name;
    
    private final long diameterInKm;
    private final long speedKmHour;
    private final Date approachDate;
    private final String orbitingBody;
    
    public Asteroid(String name, long diameterInKm, long speedKmHour, Date approachDate, String orbitingBody) {
        this.name = name;
        this.diameterInKm = diameterInKm;
        this.speedKmHour = speedKmHour;
        this.approachDate = approachDate;
        this.orbitingBody = orbitingBody;
	}
    public String getName() {
        return name;
    }
    
    public long getDiameterInKm() {
        return diameterInKm;
    }
    
    public long getSpeedKmHour() {
        return speedKmHour;
    }
    
    public Date getApproachDate() {
        return approachDate;
    }
    
    public String getOrbitingBody() {
        return orbitingBody;
    }
}
