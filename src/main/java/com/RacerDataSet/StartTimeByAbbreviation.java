package com.RacerDataSet;

public class StartTimeByAbbreviation {
    private String racerAbbreviation;
    private String startTimeOfRacer;
    StartTimeByAbbreviation(String racerAbbreviation,String startTimeOfRacer){
        this.racerAbbreviation = racerAbbreviation;
        this.startTimeOfRacer = startTimeOfRacer;
    }

    public String getRacerAbbreviation() {
        return racerAbbreviation;
    }

    public String getStartTimeOfRacer() {
        return startTimeOfRacer;
    }
}
