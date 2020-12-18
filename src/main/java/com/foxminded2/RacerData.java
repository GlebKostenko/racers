package com.foxminded2;

import com.foxminded1.RacersFile;

public class RacerData {
    private String[] racerNameAndHisCar;
    private String topTime;
    RacerData(String[] racerNameAndHisCar,String topTime){
        this.racerNameAndHisCar = racerNameAndHisCar;
        this.topTime = topTime;
    }

    public String[] getRacerNameAndHisCar(){
        return racerNameAndHisCar;
    }
    public String getTopTime(){
        return topTime;
    }
}
