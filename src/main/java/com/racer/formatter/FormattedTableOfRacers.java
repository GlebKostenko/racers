package com.racer.formatter;

import com.racer.dataset.RacersFile;
import com.racer.service.RacerData;
import com.racer.service.RacersService;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;
import java.util.stream.Collectors;

public class FormattedTableOfRacers {
    private RacersService implementation;
    private String fileNameAbbreviations;
    private String fileNameStart;
    private String fileNameEnd;
    public FormattedTableOfRacers(RacersFile impl,String fileNameAbbreviations,String fileNameStart,String fileNameEnd){
        implementation = new RacersService(impl);
        this.fileNameAbbreviations =fileNameAbbreviations;
        this.fileNameStart = fileNameStart;
        this.fileNameEnd = fileNameEnd;
    }
    public String makeTableOfRacers() throws IOException , URISyntaxException {
        StringBuilder result = new StringBuilder();
        List<RacerData> sortedTable = implementation.makeRacersTable(fileNameAbbreviations,fileNameStart,fileNameEnd);
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
