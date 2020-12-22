package com.racer.dataset;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;

public interface RacersFile {
    List<ParsedAbbreviations> parseAbbreviations(String fileName) throws IOException, URISyntaxException;
    List<StartTimeByAbbreviation> parseStartDataSet(String fileName) throws IOException,URISyntaxException;
    List<EndTimeByAbbreviation> parseEndDataSet(String fileName) throws IOException,URISyntaxException;
}
