package com.racer.dataset;

import java.util.Objects;

public class EndTimeByAbbreviation {
    private String racerAbbreviation;
    private String endTimeOfRacer;
    public EndTimeByAbbreviation(String racerAbbreviation,String endTimeOfRacer){
        this.racerAbbreviation = racerAbbreviation;
        this.endTimeOfRacer = endTimeOfRacer;
    }

    public String getRacerAbbreviation() {
        return racerAbbreviation;
    }

    public String getEndTimeOfRacer() {
        return endTimeOfRacer;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EndTimeByAbbreviation that = (EndTimeByAbbreviation) o;
        return Objects.equals(racerAbbreviation, that.racerAbbreviation) && Objects.equals(endTimeOfRacer, that.endTimeOfRacer);
    }

    @Override
    public int hashCode() {
        return Objects.hash(racerAbbreviation, endTimeOfRacer);
    }
}
