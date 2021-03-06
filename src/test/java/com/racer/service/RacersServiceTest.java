package com.racer.service;

import com.racer.dataset.EndTimeByAbbreviation;
import com.racer.dataset.ParsedAbbreviations;
import com.racer.dataset.RacersFile;
import com.racer.dataset.StartTimeByAbbreviation;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;

class RacersServiceTest {
    @Mock
    private RacersFile dataSet;
    private RacersService serviceOfRacerDataSet;
    RacersServiceTest(){
        MockitoAnnotations.initMocks(this);
        serviceOfRacerDataSet = new RacersService(dataSet);
    }

    @Test
    void makeRacersTable() throws IOException, URISyntaxException {
        given(dataSet.parseAbbreviations())
                .willReturn(Arrays.asList
                (new ParsedAbbreviations("BHS", "Brendon Hartley", "SCUDERIA TORO ROSSO HONDA"),
                new ParsedAbbreviations("MES", "Marcus Ericsson", "SAUBER FERRARI")));
        given(dataSet.parseStartDataSet()).willReturn(Arrays.asList(
                                                      new StartTimeByAbbreviation
                                                      ("BHS","12:14:51.985"),
                                                      new StartTimeByAbbreviation
                                                      ("MES","12:04:45.513")));
        given(dataSet.parseEndDataSet()).willReturn(Arrays.asList(
                                                      new EndTimeByAbbreviation
                                                      ("BHS","12:16:05.164"),
                                                      new EndTimeByAbbreviation
                                                      ("MES","12:05:58.778")));
        List<RacerData> expected = Arrays.asList(
                new RacerData("Brendon Hartley","SCUDERIA TORO ROSSO HONDA","01:13.179"),
                new RacerData("Marcus Ericsson","SAUBER FERRARI","01:13.265"));
        assertEquals(expected, serviceOfRacerDataSet.makeRacersTable());

    }
}