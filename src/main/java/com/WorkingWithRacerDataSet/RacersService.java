package com.WorkingWithRacerDataSet;

import com.RacerDataSet.EndTimeByAbbreviation;
import com.RacerDataSet.ParsedAbbreviations;
import com.RacerDataSet.RacersFile;
import com.RacerDataSet.StartTimeByAbbreviation;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
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
        List<RacerData> infAboutRacers = parsedAbbreviations.stream().map(x->{
            String abbreviationOfRacer = x.getAbbreviationOfRacer();
            String startTimeForRacer = parsedStartTime.stream()
                    .filter(p -> p.getRacerAbbreviation().equals(abbreviationOfRacer)).findAny().get().getStartTimeOfRacer();
            String endTimeForRacer = parsedEndTime.stream()
                    .filter(p -> p.getRacerAbbreviation().equals(abbreviationOfRacer)).findAny().get().getEndTimeOfRacer();
            String topTime = calculateTopTimeForRacer(startTimeForRacer,endTimeForRacer);
            RacerData racer = new RacerData(x.getRacerName(),x.getRacerCar(),topTime);
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
        StringBuilder result = new StringBuilder();
        String[] parsedStartTime = startTime.split(":");
        String[] parsedEndTime = endTime.split(":");
        if (!parsedStartTime[0].equals(parsedEndTime[0])) {
            int deltaHours = Integer.parseInt(parsedEndTime[0]) - Integer.parseInt(parsedStartTime[0]);
            if (deltaHours < 10) {
                result.append("0" + deltaHours + ":");
            } else {
                result.append(deltaHours + ":");
            }
        }
        int deltaMinutes = Integer.parseInt(parsedEndTime[1]) - Integer.parseInt(parsedStartTime[1]);
        double deltaSeconds = Double.parseDouble(parsedEndTime[2]) - Double.parseDouble(parsedStartTime[2]);
        if (deltaSeconds < 0) {
            deltaMinutes = deltaMinutes - 1;
            deltaSeconds = 60.0 - Math.abs(deltaSeconds);
        }
        if (deltaMinutes < 10) {
            result.append("0" + deltaMinutes + ":");
            if (deltaSeconds < 10.0) {
                result.append("0");
            }
            result.append(String.format("%.3f", deltaSeconds));
        } else {
            result.append(deltaMinutes + ":");
            if (deltaSeconds < 10.0) {
                result.append("0");
            }
            result.append(String.format("%.3f", deltaSeconds));
        }
        return result.toString();
    }

}