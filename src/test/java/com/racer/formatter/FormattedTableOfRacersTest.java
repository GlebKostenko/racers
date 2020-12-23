package com.racer.formatter;

import com.racer.dataset.*;
import com.racer.service.RacerData;
import com.racer.service.RacersService;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;

class FormattedTableOfRacersTest {
    @Mock
    private RacersFile dataSet;
    private RacersService impl;
    private FormattedTableOfRacers test;
    FormattedTableOfRacersTest(){
        MockitoAnnotations.initMocks(this);
        impl = new RacersService(dataSet);
        test = new FormattedTableOfRacers(dataSet,impl);
    }
    @Test
    void makeTableOfRacers() throws IOException, URISyntaxException {
        given(dataSet.parseAbbreviations())
                .willReturn(Arrays.asList
                        (new ParsedAbbreviations("BHS", "Brendon Hartley", "SCUDERIA TORO ROSSO HONDA"),
                                new ParsedAbbreviations("MES", "Marcus Ericsson", "SAUBER FERRARI"))
                );
        given(dataSet.parseStartDataSet()).willReturn(Arrays.asList(
                new StartTimeByAbbreviation
                        ("BHS","12:14:51.985"),
                new StartTimeByAbbreviation
                        ("MES","12:04:45.513"))
                );
        given(dataSet.parseEndDataSet()).willReturn(Arrays.asList(
                new EndTimeByAbbreviation
                        ("BHS","12:16:05.164"),
                new EndTimeByAbbreviation
                        ("MES","12:05:58.778"))
                );
        String expected = " 1. Brendon Hartley     |SCUDERIA TORO ROSSO HONDA |01:13.179\n"
                + " 2. Marcus Ericsson     |SAUBER FERRARI            |01:13.265\n"
                + "-------------------------------------------------------------\n";
        assertEquals(expected,test.makeTableOfRacers());
    }
}