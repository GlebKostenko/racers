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
        return sortedTable.stream().map(x->{
            if(sortedTable.indexOf(x) + 1 != 15){
                int racerPos = sortedTable.indexOf(x) + 1;
                return String.format("%3s %-20s|%-26s|%s\n",racerPos+".",x.getRacerName(),
                        x.getRacerCar(),
                        x.getTopTime());
            }else {
                int racerPos = sortedTable.indexOf(x) + 1;
                String racerInfAboutTopCircle =  String.format("%3s %-20s|%-26s|%s\n",racerPos+".",x.getRacerName(),
                        x.getRacerCar(),
                        x.getTopTime());
                Integer  n = racerInfAboutTopCircle.length() - 1;
                String symbol ="-";
                String line = new String(new char[n]).replace("\0", symbol);
                return racerInfAboutTopCircle + line + "\n";
            }

        }).collect(Collectors.joining());
    }
}
