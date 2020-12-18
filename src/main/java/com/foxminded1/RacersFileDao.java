package com.foxminded1;


import javafx.util.Pair;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class RacersFileDao implements RacersFile {

    Path pathOfStart = Paths.get("src/main/resources/start.log");
    Path pathOfEnd = Paths.get("src/main/resources/end.log");
    Path pathOfAbbs = Paths.get("src/main/resources/abbreviations.txt");

    public List<String> makeStartDataSet() throws IOException {
        Stream<String> infAboutStartTime = Files.lines(pathOfStart);
        return infAboutStartTime.sorted().collect(Collectors.toList());
    }

    public List<String> makeEndDataSet() throws IOException {
        Stream<String> infAboutEndTime = Files.lines(pathOfEnd);
        return infAboutEndTime.sorted().collect(Collectors.toList());
    }

    public List<String> makeAbbreviationsDataSet() throws IOException {
        Stream<String> infAboutAbbreviations = Files.lines(pathOfAbbs);
        return infAboutAbbreviations.sorted().collect(Collectors.toList());

    }

    public List<Pair<String, String[]>> parseAbbreviations() throws IOException {
        List<String> abbreviations = makeAbbreviationsDataSet();
        List<Pair<String, String[]>> parsedAbbs = abbreviations.stream().map(x->{
            String[] infoAboutRacer = x.split("_");
            String racerAbbreviation = infoAboutRacer[0];
            Pair<String,String[]> racerNameAndHisCarByAbbreviation =
                    new Pair<>(racerAbbreviation,new String[]{infoAboutRacer[1],infoAboutRacer[2]});
            return racerNameAndHisCarByAbbreviation;
        }).collect(Collectors.toList());
        Collections.sort(parsedAbbs, new Comparator<Pair<String, String[]>>() {
            @Override
            public int compare(Pair<String, String[]> o1, Pair<String, String[]> o2) {
                return o1.getKey().compareTo(o2.getKey());
            }
        });
        return parsedAbbs;
    }

    public List<Pair<String, String>> parseStartDataSet() throws IOException{
        List<Pair<String, String>> infAboutStartTime = makeStartDataSet().stream().map(x->{
            String racerAbbreviation = x.substring(0,3);
            String startTime = getStartOrEndTimeFromString(x);
            Pair<String,String> startTimeOfRacer =
                    new Pair<>(racerAbbreviation, startTime);
            return startTimeOfRacer;
        }).collect(Collectors.toList());
        Collections.sort(infAboutStartTime, new Comparator<Pair<String, String>>() {
            @Override
            public int compare(Pair<String, String> o1, Pair<String, String> o2) {
                return o1.getKey().compareTo(o2.getKey());
            }
        });
        return infAboutStartTime;
    }

    public List<Pair<String, String>> parseEndDataSet() throws IOException{
        List<Pair<String, String>> infAboutEndTime = makeEndDataSet().stream().map(x->{
            String racerAbbreviation = x.substring(0,3);
            String endTime = getStartOrEndTimeFromString(x);
            Pair<String,String> startTimeOfRacer =
                    new Pair<>(racerAbbreviation, endTime);
            return startTimeOfRacer;
        }).collect(Collectors.toList());
        Collections.sort(infAboutEndTime, new Comparator<Pair<String, String>>() {
            @Override
            public int compare(Pair<String, String> o1, Pair<String, String> o2) {
                return o1.getKey().compareTo(o2.getKey());
            }
        });
        return infAboutEndTime;
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

    public String getStartOrEndTimeFromString(String infAboutRacerStartOrEnd) {
        String[] parsedTime = infAboutRacerStartOrEnd.split("_");
        return parsedTime[1];
    }


}
