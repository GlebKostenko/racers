package com.racer.dataset;


import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class RacersFileDao implements RacersFile {
    private Path pathOfStart;
    private Path pathOfEnd ;
    private Path pathOfAbbs;
    public RacersFileDao(String abbsFile,String startFile,String endFile) throws URISyntaxException{
        pathOfAbbs = Paths.get(getFileFromResource(abbsFile).getPath());
        pathOfStart = Paths.get(getFileFromResource(startFile).getPath());
        pathOfEnd = Paths.get(getFileFromResource(endFile).getPath());

    }
    public List<ParsedAbbreviations> parseAbbreviations() throws IOException{
        return Files.lines(pathOfAbbs).map(x->{
                    String[] infoAboutRacer = x.split("_");
                    String racerAbbreviation = infoAboutRacer[0];
                    ParsedAbbreviations racerNameAndHisCarByAbbreviation =
                            new ParsedAbbreviations(racerAbbreviation,infoAboutRacer[1],infoAboutRacer[2]);
                    return racerNameAndHisCarByAbbreviation;
                }).sorted(new Comparator<ParsedAbbreviations>() {
                    @Override
                    public int compare(ParsedAbbreviations o1, ParsedAbbreviations o2) {
                        return o1.getAbbreviationOfRacer().compareTo(o2.getAbbreviationOfRacer());
                    }
                }).collect(Collectors.toList());
    }

    public List<StartTimeByAbbreviation> parseStartDataSet() throws IOException{
        return Files.lines(pathOfStart).map(x->{
                    String racerAbbreviation = x.substring(0,3);
                    String startTime = getStartOrEndTimeFromString(x);
                    StartTimeByAbbreviation startTimeOfRacer =
                            new StartTimeByAbbreviation(racerAbbreviation, startTime);
                    return startTimeOfRacer;
                }).sorted(new Comparator<StartTimeByAbbreviation>() {
                    @Override
                    public int compare(StartTimeByAbbreviation o1, StartTimeByAbbreviation o2) {
                        return o1.getRacerAbbreviation().compareTo(o2.getRacerAbbreviation());
                    }
                }).collect(Collectors.toList());

    }

    public List<EndTimeByAbbreviation> parseEndDataSet() throws IOException{
        return Files.lines(pathOfEnd).map(x->{
                    String racerAbbreviation = x.substring(0,3);
                    String endTime = getStartOrEndTimeFromString(x);
                    EndTimeByAbbreviation startTimeOfRacer =
                            new EndTimeByAbbreviation(racerAbbreviation, endTime);
                    return startTimeOfRacer;
                }).sorted(new Comparator<EndTimeByAbbreviation>() {
                    @Override
                    public int compare(EndTimeByAbbreviation o1, EndTimeByAbbreviation o2) {
                        return o1.getRacerAbbreviation().compareTo(o2.getRacerAbbreviation());
                    }
                }).collect(Collectors.toList());

    }

    private String getStartOrEndTimeFromString(String infAboutRacerStartOrEnd) {
        String[] parsedTime = infAboutRacerStartOrEnd.split("_");
        return parsedTime[1];
    }

    private File getFileFromResource(String fileName) throws URISyntaxException {
        ClassLoader classLoader = getClass().getClassLoader();
        URL resource = classLoader.getResource(fileName);
        if (resource == null) {
            throw new IllegalArgumentException("file not found! " + fileName);
        } else {
            return new File(resource.toURI());
        }

    }

}
