package com.foxminded;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
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

    public HashMap<String, String[]> parseAbbreviations() throws IOException {
        List<String> abbreviations = makeAbbreviationsDataSet();
        HashMap<String, String[]> result = new HashMap<>();
        abbreviations.stream().forEach(x->{
            String[] infoAboutRacer = x.split("_");
            String racerAbbreviation = infoAboutRacer[0];
            result.put(racerAbbreviation, new String[]{infoAboutRacer[1],infoAboutRacer[2]});
        });
        return result;
    }

    public HashMap<String,String> parseStartDataSet() throws IOException{
        HashMap<String,String> result = new HashMap<>();
        makeStartDataSet().stream().forEach(x->{
            String racerAbbreviation = x.substring(0,3);
            String startTime = getStartOrEndTimeFromString(x);
            result.put(racerAbbreviation,startTime);
        });
        return result;
    }

    public HashMap<String,String> parseEndDataSet() throws IOException{
        HashMap<String,String> result = new HashMap<>();
        makeEndDataSet().stream().forEach(x->{
            String racerAbbreviation = x.substring(0,3);
            String endTime = getStartOrEndTimeFromString(x);
            result.put(racerAbbreviation,endTime);
        });
        return result;
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
