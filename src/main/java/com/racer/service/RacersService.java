package com.racer.service;

import com.racer.dataset.EndTimeByAbbreviation;
import com.racer.dataset.ParsedAbbreviations;
import com.racer.dataset.RacersFile;
import com.racer.dataset.StartTimeByAbbreviation;

import java.io.IOException;
import java.net.URISyntaxException;
import java.time.Duration;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;


public class RacersService {

    private RacersFile implementation;

    public RacersService(RacersFile impl) {
        implementation = impl;
    }

    public List<RacerData> makeRacersTable() throws IOException {
        List<ParsedAbbreviations> parsedAbbreviations = implementation.parseAbbreviations();
        List<StartTimeByAbbreviation> parsedStartTime = implementation.parseStartDataSet();
        List<EndTimeByAbbreviation> parsedEndTime = implementation.parseEndDataSet();
        return parsedAbbreviations.stream().map(x -> {
            String abbreviationOfRacer = x.getAbbreviationOfRacer();
            String startTimeForRacer = parsedStartTime.stream()
                    .filter(p -> p.getRacerAbbreviation().equals(abbreviationOfRacer)).findAny().get().getStartTimeOfRacer();
            String endTimeForRacer = parsedEndTime.stream()
                    .filter(p -> p.getRacerAbbreviation().equals(abbreviationOfRacer)).findAny().get().getEndTimeOfRacer();
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

}