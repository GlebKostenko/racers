package com.racer.dataset;

import java.util.Objects;

public class StartTimeByAbbreviation {
    private String racerAbbreviation;
    private String startTimeOfRacer;
    public StartTimeByAbbreviation(String racerAbbreviation,String startTimeOfRacer){
        this.racerAbbreviation = racerAbbreviation;
        this.startTimeOfRacer = startTimeOfRacer;
    }

    public String getRacerAbbreviation() {
        return racerAbbreviation;
    }

    public String getStartTimeOfRacer() {
        return startTimeOfRacer;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        StartTimeByAbbreviation that = (StartTimeByAbbreviation) o;
        return Objects.equals(racerAbbreviation, that.racerAbbreviation) && Objects.equals(startTimeOfRacer, that.startTimeOfRacer);
    }

    @Override
    public int hashCode() {
        return Objects.hash(racerAbbreviation, startTimeOfRacer);
    }
}
