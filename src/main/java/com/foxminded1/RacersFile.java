package com.foxminded1;

import javafx.util.Pair;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

public interface RacersFile {
    List<String> makeStartDataSet() throws IOException;
    List<String> makeEndDataSet() throws IOException;
    List<String> makeAbbreviationsDataSet() throws IOException;
    List<Pair<String, String[]>> parseAbbreviations() throws IOException;
    List<Pair<String, String>> parseStartDataSet() throws IOException;
    List<Pair<String, String>> parseEndDataSet() throws IOException;
    String calculateTopTimeForRacer(String startTime, String endTime);
    String getStartOrEndTimeFromString(String infAboutRacerStartOrEnd);
}
