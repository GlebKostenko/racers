package com.RacerDataSet;

public class EndTimeByAbbreviation {
    private String racerAbbreviation;
    private String endTimeOfRacer;
    EndTimeByAbbreviation(String racerAbbreviation,String endTimeOfRacer){
        this.racerAbbreviation = racerAbbreviation;
        this.endTimeOfRacer = endTimeOfRacer;
    }

    public String getRacerAbbreviation() {
        return racerAbbreviation;
    }

    public String getEndTimeOfRacer() {
        return endTimeOfRacer;
    }
}
