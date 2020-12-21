package com.WorkingWithRacerDataSet;

import com.RacerDataSet.EndTimeByAbbreviation;
import com.RacerDataSet.ParsedAbbreviations;
import com.RacerDataSet.RacersFile;
import com.RacerDataSet.StartTimeByAbbreviation;

import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.*;
import java.util.stream.Collectors;

import static java.time.LocalTime.ofNanoOfDay;


public class RacersService {

    private RacersFile implementation;

    public RacersService(RacersFile impl) {
        implementation = impl;
    }

    public List<RacerData> makeRacersTable() throws IOException {
        List<ParsedAbbreviations> parsedAbbreviations = implementation.parseAbbreviations();
        List<StartTimeByAbbreviation> parsedStartTime = implementation.parseStartDataSet();
        List<EndTimeByAbbreviation> parsedEndTime = implementation.parseEndDataSet();
        List<RacerData> infAboutRacers = parsedAbbreviations.stream().map(x -> {
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
        return infAboutRacers;
    }

    public String calculateTopTimeForRacer(String startTime, String endTime) {
        LocalTime oldDate = LocalTime.parse(startTime);
        LocalTime newDate = LocalTime.parse(endTime);
        return LocalTime.ofNanoOfDay(Duration.between(oldDate,newDate).toNanos())
                .format(DateTimeFormatter.ofPattern("mm:ss.SSS"));
    }
}