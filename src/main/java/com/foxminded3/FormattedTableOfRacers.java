package com.foxminded3;

import com.foxminded1.RacersFile;
import com.foxminded2.RacerData;
import com.foxminded2.RacersService;
import java.io.IOException;
import java.util.List;
import java.util.Map.Entry;
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
                return String.format("%3s %-20s|%-26s|%s\n",racerPos+".",x.getRacerNameAndHisCar()[0],
                                                                         x.getRacerNameAndHisCar()[1],
                                                                         x.getTopTime());
            }else {
                return "------------------------------------------------------------------------\n";
            }
        }).collect(Collectors.joining());
    }
}
