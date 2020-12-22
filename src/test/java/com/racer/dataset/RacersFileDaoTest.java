package com.racer.dataset;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class RacersFileDaoTest {

    private RacersFileDao test = new RacersFileDao();

    @Test
    void parseAbbreviations() throws IOException, URISyntaxException {
        List<ParsedAbbreviations> expected = new ArrayList<ParsedAbbreviations>();
        ParsedAbbreviations first = new ParsedAbbreviations
                ("BHS","Brendon Hartley","SCUDERIA TORO ROSSO HONDA");
        ParsedAbbreviations second = new ParsedAbbreviations
                ("MES","Marcus Ericsson","SAUBER FERRARI");
        expected.add(first);
        expected.add(second);
        assertEquals(expected,test.parseAbbreviations("testAbbreviations.txt"));

    }

    @Test
    void parseStartDataSet() throws IOException, URISyntaxException {
        List<StartTimeByAbbreviation> expected = new ArrayList<StartTimeByAbbreviation>();
        StartTimeByAbbreviation first = new StartTimeByAbbreviation
                ("BHS","12:14:51.985");
        StartTimeByAbbreviation second = new StartTimeByAbbreviation
                ("MES","12:04:45.513");
        expected.add(first);
        expected.add(second);
        assertEquals(expected,test.parseStartDataSet("testStart.log"));
    }

    @Test
    void parseEndDataSet() throws IOException, URISyntaxException {
        List<EndTimeByAbbreviation> expected = new ArrayList<EndTimeByAbbreviation>();
        EndTimeByAbbreviation first = new EndTimeByAbbreviation
                ("BHS","12:16:05.164");
        EndTimeByAbbreviation second = new EndTimeByAbbreviation
                ("MES","12:05:58.778");
        expected.add(first);
        expected.add(second);
        assertEquals(expected,test.parseEndDataSet("testEnd.log"));
    }
}