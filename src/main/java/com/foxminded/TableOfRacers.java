package com.foxminded;

import java.io.IOException;
import java.nio.file.Path;
import java.util.*;
import java.util.Map.Entry;

public class TableOfRacers {

    private GetData dataSet;
    private WorkWithDataSet implementation = new WorkWithDataSet();

    public TableOfRacers(Path pathOfStart,Path pathOfEnd,Path pathOfAbbs) {
        dataSet = new GetData(pathOfStart, pathOfEnd, pathOfAbbs);
    }

    public String makeRacersTable() throws IOException {
        HashMap<String,String> infAboutRacers = new HashMap<>();
        HashMap<String,String> parsedAbbreviations = implementation.
                                                     parseAbbreviations
                                                     (dataSet.makeAbbreviationsDataSet());
        List<String> startTime = dataSet.makeStartDataSet();
        List<String> endTime = dataSet.makeEndDataSet();
        for(int i = 0; i < startTime.size();++i){
            String abbreviationOfRacer = startTime.get(i).substring(0,3);
            String startTimeForRacer = implementation.getStartOrEndTimeFromString(startTime.get(i));
            String endTimeForRacer = implementation.getStartOrEndTimeFromString(endTime.get(i));
            String topTime = implementation
                             .calculateTopTimeForRacer(startTimeForRacer,endTimeForRacer);
            infAboutRacers.put(parsedAbbreviations.get(abbreviationOfRacer) + "|",topTime);
        }

        Set<Entry<String, String>> set = infAboutRacers.entrySet();
        List<Entry<String, String>> sortedTable = new ArrayList<Entry<String, String>>(
                set);
        Collections.sort(sortedTable, new Comparator<Entry<String, String>>() {
            public int compare(Map.Entry<String, String> o1,
                               Map.Entry<String, String> o2) {
                return o1.getValue().compareTo(o2.getValue());
            }
        });

        StringBuilder result = new StringBuilder();

        for(int i = 0;i < sortedTable.size();++i){
            result.append(String.format("%3s %s%s\n",(i+1) + ".",
                                                    sortedTable.get(i).getKey(),
                                                    sortedTable.get(i).getValue()));
            if(i == 14){
                result.append("------------------------------------------------------------------------\n");
            }
        }
        return result.toString();
    }
}