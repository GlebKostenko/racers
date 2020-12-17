package com.foxminded;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

public class WorkWithDataSet {

    public HashMap<String,String> parseAbbreviations(List<String> abbreviations){
        HashMap<String,String> result = new HashMap<>();
        for(String racer : abbreviations){
            String[] infoAboutRacer = racer.split("_");
            String racerAbbreviation = infoAboutRacer[0];
            String racerNameAndHisCar = String.format("%-20s|%-26s",infoAboutRacer[1],infoAboutRacer[2]);
            result.put(racerAbbreviation,racerNameAndHisCar);
        }
        return result;
    }

    public String calculateTopTimeForRacer(String startTime, String endTime){
        StringBuilder result = new StringBuilder();
        String[] parsedStartTime = startTime.split(":");
        String[] parsedEndTime = endTime.split(":");
        if(!parsedStartTime[0].equals(parsedEndTime[0])){
            int deltaHours = Integer.parseInt(parsedEndTime[0]) - Integer.parseInt(parsedStartTime[0]);
            if(deltaHours < 10){
                result.append("0" + deltaHours + ":");
            }else {
                result.append(deltaHours + ":");
            }
        }
        int deltaMinutes = Integer.parseInt(parsedEndTime[1]) - Integer.parseInt(parsedStartTime[1]);
        double deltaSeconds = Double.parseDouble(parsedEndTime[2]) - Double.parseDouble(parsedStartTime[2]);
        if(deltaSeconds < 0 ){
            deltaMinutes = deltaMinutes - 1;
            deltaSeconds = 60.0 - Math.abs(deltaSeconds);
        }
        if(deltaMinutes < 10){
            result.append("0" + deltaMinutes + ":");
            if(deltaSeconds < 10.0){
                result.append("0");
            }
            result.append(String.format("%.3f",deltaSeconds));
        }else {
            result.append(deltaMinutes + ":");
            if(deltaSeconds < 10.0){
                result.append("0");
            }
            result.append(String.format("%.3f",deltaSeconds));
        }
        return result.toString();
    }

    public String getStartOrEndTimeFromString(String infAboutRacerStartOrEnd){
        String[] parsedTime = infAboutRacerStartOrEnd.split("_");
        return parsedTime[1];
    }

}
