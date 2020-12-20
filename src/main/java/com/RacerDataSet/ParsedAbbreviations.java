package com.RacerDataSet;

public class ParsedAbbreviations {
    private String abbreviationOfRacer;
    private String racerName;
    private String racerCar;
    ParsedAbbreviations(String abbreviationOfRacer,String racerName,String racerCar){
        this.abbreviationOfRacer =abbreviationOfRacer;
        this.racerName = racerName;
        this.racerCar = racerCar;
    }

    public String getAbbreviationOfRacer() {
        return abbreviationOfRacer;
    }

    public String getRacerName() {
        return racerName;
    }

    public String getRacerCar() {
        return racerCar;
    }
}
