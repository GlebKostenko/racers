package com.WorkingWithRacerDataSet;

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
}
