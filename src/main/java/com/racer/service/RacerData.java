package com.racer.service;

import java.util.Objects;

public class RacerData {
    private String racerName;
    private String racerCar;
    private String topTime;
    RacerData(String racerName,String racerCar,String topTime){
        this.racerName = racerName;
        this.racerCar = racerCar;
        this.topTime = topTime;
    }

    public String getRacerName() {
        return racerName;
    }

    public String getRacerCar() {
        return racerCar;
    }

    public String getTopTime(){
        return topTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RacerData racerData = (RacerData) o;
        return Objects.equals(racerName, racerData.racerName) && Objects.equals(racerCar, racerData.racerCar) && Objects.equals(topTime, racerData.topTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(racerName, racerCar, topTime);
    }
}
