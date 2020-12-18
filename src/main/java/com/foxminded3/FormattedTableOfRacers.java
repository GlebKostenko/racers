package com.foxminded3;

import com.foxminded1.RacersFile;
import com.foxminded2.RacersService;
import java.io.IOException;
import java.util.List;
import java.util.Map.Entry;

public class FormattedTableOfRacers {
    RacersService implementation;
    public FormattedTableOfRacers(RacersFile impl){
        implementation = new RacersService(impl);
    }
    public String makeTableOfRacers() throws IOException {
        StringBuilder result = new StringBuilder();
        List<Entry<String[], String>> sortedTable = implementation.makeRacersTable();
        sortedTable.stream().forEach(x->{
            int racerPos = sortedTable.indexOf(x) + 1;
            result.append(String.format("%3s %-20s|%-26s|%s\n",racerPos+".",x.getKey()[0],x.getKey()[1],x.getValue()));
            if(racerPos == 15){
                result.append("------------------------------------------------------------------------\n");
            }
        });
        return result.toString();
    }
}
