package com.FormatTheResultOfWorking;

import com.RacerDataSet.RacersFile;
import com.WorkingWithRacerDataSet.RacerData;
import com.WorkingWithRacerDataSet.RacersService;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

public class FormattedTableOfRacers {
    RacersService implementation;
    public FormattedTableOfRacers(RacersFile impl){
        implementation = new RacersService(impl);
    }
    public String makeTableOfRacers() throws IOException {
        StringBuilder result = new StringBuilder();
        List<RacerData> sortedTable = implementation.makeRacersTable();
         String firstFiveteenPositions= sortedTable.stream().limit(15).map(x->{
                int racerPos = sortedTable.indexOf(x) + 1;
                return String.format("%3s %-20s|%-26s|%s\n",racerPos+".",x.getRacerName(),
                        x.getRacerCar(),
                        x.getTopTime());
                }).collect(Collectors.joining());
        Integer  n = firstFiveteenPositions.indexOf("\n");
        String symbol ="-";
        String line = new String(new char[n]).replace("\0", symbol) + "\n";
        String lastPositions= sortedTable.stream().skip(15).map(x-> {
            int racerPos = sortedTable.indexOf(x) + 1;
            return String.format("%3s %-20s|%-26s|%s\n", racerPos + ".", x.getRacerName(),
                    x.getRacerCar(),
                    x.getTopTime());
        }).collect(Collectors.joining());
        return firstFiveteenPositions  + line + lastPositions;
    }

}
