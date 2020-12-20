package com.RacerDataSet;

import java.io.IOException;
import java.util.List;

public interface RacersFile {
    List<String> makeStartDataSet() throws IOException;
    List<String> makeEndDataSet() throws IOException;
    List<String> makeAbbreviationsDataSet() throws IOException;
    List<ParsedAbbreviations> parseAbbreviations() throws IOException;
    List<StartTimeByAbbreviation> parseStartDataSet() throws IOException;
    List<EndTimeByAbbreviation> parseEndDataSet() throws IOException;
}
