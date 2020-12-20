package com.RacerDataSet;


import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class RacersFileDao implements RacersFile {

    Path pathOfStart = Paths.get("src/main/resources/start.log");
    Path pathOfEnd = Paths.get("src/main/resources/end.log");
    Path pathOfAbbs = Paths.get("src/main/resources/abbreviations.txt");

    public List<String> makeStartDataSet() throws IOException {
        Stream<String> infAboutStartTime = Files.lines(pathOfStart);
        return infAboutStartTime.sorted().collect(Collectors.toList());
    }

    public List<String> makeEndDataSet() throws IOException {
        Stream<String> infAboutEndTime = Files.lines(pathOfEnd);
        return infAboutEndTime.sorted().collect(Collectors.toList());
    }

    public List<String> makeAbbreviationsDataSet() throws IOException {
        Stream<String> infAboutAbbreviations = Files.lines(pathOfAbbs);
        return infAboutAbbreviations.sorted().collect(Collectors.toList());

    }

    public List<ParsedAbbreviations> parseAbbreviations() throws IOException {
        List<String> abbreviations = makeAbbreviationsDataSet();
        List<ParsedAbbreviations> parsedAbbs = abbreviations.stream().map(x->{
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
        return parsedAbbs;
    }

    public List<StartTimeByAbbreviation> parseStartDataSet() throws IOException{
        List<StartTimeByAbbreviation> infAboutStartTime = makeStartDataSet().stream().map(x->{
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
        return infAboutStartTime;
    }

    public List<EndTimeByAbbreviation> parseEndDataSet() throws IOException{
        List<EndTimeByAbbreviation> infAboutEndTime = makeEndDataSet().stream().map(x->{
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
        return infAboutEndTime;
    }

    private String getStartOrEndTimeFromString(String infAboutRacerStartOrEnd) {
        String[] parsedTime = infAboutRacerStartOrEnd.split("_");
        return parsedTime[1];
    }

}
