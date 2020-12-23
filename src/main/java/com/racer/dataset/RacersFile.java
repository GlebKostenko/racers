package com.racer.dataset;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;

public interface RacersFile {
    List<ParsedAbbreviations> parseAbbreviations() throws IOException;
    List<StartTimeByAbbreviation> parseStartDataSet() throws IOException;
    List<EndTimeByAbbreviation> parseEndDataSet() throws IOException;
}
