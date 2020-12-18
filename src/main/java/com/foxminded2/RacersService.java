package com.foxminded2;

import com.foxminded1.RacersFile;

import java.io.IOException;
import java.util.*;
import java.util.Map.Entry;


public class RacersService {

    private RacersFile implementation;

    public RacersService(RacersFile impl) {
        implementation = impl;
    }

    public List<Entry<String[], String>> makeRacersTable() throws IOException {
        HashMap<String[],String> infAboutRacers = new HashMap<>();
        HashMap<String,String[]> parsedAbbreviations = implementation.parseAbbreviations();
        HashMap<String,String> parsedStartTime = implementation.parseStartDataSet();
        HashMap<String,String> parsedEndTime = implementation.parseEndDataSet();
        Set<String> abbreviationsAsKeys = parsedAbbreviations.keySet();
        abbreviationsAsKeys.stream().forEach(x->{
            String abbreviationOfRacer = x;
            String startTimeForRacer = parsedStartTime.get(x);
            String endTimeForRacer = parsedEndTime.get(x);
            String topTime = implementation
                    .calculateTopTimeForRacer(startTimeForRacer,endTimeForRacer);
            infAboutRacers.put(parsedAbbreviations.get(x),topTime);
        });

        Set<Entry<String[], String>> set = infAboutRacers.entrySet();
        List<Entry<String[], String>> sortedTable = new ArrayList<Entry<String[], String>>(
                set);
        Collections.sort(sortedTable, new Comparator<Entry<String[], String>>() {
            public int compare(Entry<String[], String> o1,
                               Entry<String[], String> o2) {
                return o1.getValue().compareTo(o2.getValue());
            }
        });
        return sortedTable;

    }
}