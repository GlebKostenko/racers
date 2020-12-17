package com.foxminded;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class GetData {
    private Path pathOfStart;
    private Path pathOfEnd;
    private Path pathOfAbbs;
    public GetData(Path pathOfStart,Path pathOfEnd,Path pathOfAbbs){
        this.pathOfStart = pathOfStart;
        this.pathOfEnd = pathOfEnd;
        this.pathOfAbbs = pathOfAbbs;
    }

    public List<String> makeStartDataSet() throws  IOException {
        Stream<String> infAboutStartTime = Files.lines(pathOfStart);
        return  infAboutStartTime.sorted().collect(Collectors.toList());
    }

    public List<String> makeEndDataSet() throws  IOException {
        Stream<String> infAboutEndTime = Files.lines(pathOfEnd);
        return infAboutEndTime.sorted().collect(Collectors.toList());
    }
    public List<String> makeAbbreviationsDataSet() throws  IOException {
        Stream<String> infAboutAbbreviations = Files.lines(pathOfAbbs);
        return  infAboutAbbreviations.sorted().collect(Collectors.toList());


    }

}
