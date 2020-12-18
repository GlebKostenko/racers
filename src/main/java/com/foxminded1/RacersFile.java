package com.foxminded1;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

public interface RacersFile {
    List<String> makeStartDataSet() throws IOException;
    List<String> makeEndDataSet() throws IOException;
    List<String> makeAbbreviationsDataSet() throws IOException;
    HashMap<String,String[]> parseAbbreviations() throws IOException;
    HashMap<String,String> parseStartDataSet() throws IOException;
    HashMap<String,String> parseEndDataSet() throws IOException;
    String calculateTopTimeForRacer(String startTime, String endTime);
    String getStartOrEndTimeFromString(String infAboutRacerStartOrEnd);
}
