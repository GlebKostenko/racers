package com.racer.dataset;

import java.util.Objects;

public class ParsedAbbreviations {
    private String abbreviationOfRacer;
    private String racerName;
    private String racerCar;
    public ParsedAbbreviations(String abbreviationOfRacer,String racerName,String racerCar){
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ParsedAbbreviations that = (ParsedAbbreviations) o;
        return Objects.equals(abbreviationOfRacer, that.abbreviationOfRacer) && Objects.equals(racerName, that.racerName) && Objects.equals(racerCar, that.racerCar);
    }

    @Override
    public int hashCode() {
        return Objects.hash(abbreviationOfRacer, racerName, racerCar);
    }
}
