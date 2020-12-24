package com.racer.service;

import com.racer.dataset.EndTimeByAbbreviation;
import com.racer.dataset.ParsedAbbreviations;
import com.racer.dataset.RacersFile;
import com.racer.dataset.StartTimeByAbbreviation;

import java.io.IOException;
import java.time.Duration;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;


public class RacersService {

    private RacersFile dataSet;

    public RacersService(RacersFile dataSet) {
        this.dataSet = dataSet;
    }

    public List<RacerData> makeRacersTable() throws IOException{
        List<ParsedAbbreviations> parsedAbbreviations = dataSet.parseAbbreviations();
        List<StartTimeByAbbreviation> parsedStartTime = dataSet.parseStartDataSet();
        List<EndTimeByAbbreviation> parsedEndTime = dataSet.parseEndDataSet();
        return parsedAbbreviations.stream().map(x -> {
            String abbreviationOfRacer = x.getAbbreviationOfRacer();
            String startTimeForRacer = getStartTimeByAbbreviation(abbreviationOfRacer,parsedStartTime);
            String endTimeForRacer = getEndTimeByAbbreviation(abbreviationOfRacer,parsedEndTime);
            String topTime = calculateTopTimeForRacer(startTimeForRacer, endTimeForRacer);
            RacerData racer = new RacerData(x.getRacerName(), x.getRacerCar(), topTime);
            return racer;
        }).sorted(new Comparator<RacerData>() {
            @Override
            public int compare(RacerData o1, RacerData o2) {
                return o1.getTopTime().compareTo(o2.getTopTime());
            }
        }).collect(Collectors.toList());

    }

    public String calculateTopTimeForRacer(String startTime, String endTime) {
        LocalTime oldDate = LocalTime.parse(startTime);
        LocalTime newDate = LocalTime.parse(endTime);
        return LocalTime.ofNanoOfDay(Duration.between(oldDate,newDate).toNanos())
                .format(DateTimeFormatter.ofPattern("mm:ss.SSS"));
    }
    private String getStartTimeByAbbreviation(String abbreviationOfRacer,List<StartTimeByAbbreviation> parsedStartTime){
        return parsedStartTime.stream()
                .filter(p -> p.getRacerAbbreviation().equals(abbreviationOfRacer)).findAny().get().getStartTimeOfRacer();
    }

    private String getEndTimeByAbbreviation(String abbreviationOfRacer,List<EndTimeByAbbreviation> parsedEndTime){
        return parsedEndTime.stream()
                .filter(p -> p.getRacerAbbreviation().equals(abbreviationOfRacer)).findAny().get().getEndTimeOfRacer();
    }
}