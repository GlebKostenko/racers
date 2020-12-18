package com.foxminded2;

import com.foxminded1.RacersFile;
import javafx.util.Pair;

import java.io.IOException;
import java.util.*;
import java.util.Map.Entry;
import java.util.stream.Collectors;


public class RacersService {

    private RacersFile implementation;

    public RacersService(RacersFile impl) {
        implementation = impl;
    }

    public List<RacerData> makeRacersTable() throws IOException {
        List<Pair<String, String[]>> parsedAbbreviations = implementation.parseAbbreviations();
        List<Pair<String, String>> parsedStartTime = implementation.parseStartDataSet();
        List<Pair<String, String>> parsedEndTime = implementation.parseEndDataSet();
        List<RacerData> infAboutRacers = parsedAbbreviations.stream().map(x->{
            String abbreviationOfRacer = x.getKey();
            String startTimeForRacer = parsedStartTime.stream()
                    .filter(p -> p.getKey().equals(abbreviationOfRacer)).collect(Collectors.toList()).get(0).getValue();
            String endTimeForRacer = parsedEndTime.stream()
                    .filter(p -> p.getKey().equals(abbreviationOfRacer)).collect(Collectors.toList()).get(0).getValue();
            String topTime = implementation
                    .calculateTopTimeForRacer(startTimeForRacer,endTimeForRacer);
            RacerData racer = new RacerData(x.getValue(),topTime);
            return racer;
        }).collect(Collectors.toList());

        Collections.sort(infAboutRacers, new Comparator<RacerData>() {
            @Override
            public int compare(RacerData o1, RacerData o2) {
                return o1.getTopTime().compareTo(o2.getTopTime());
            }
        });
        return infAboutRacers;

    }
}