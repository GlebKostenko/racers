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
    private RacersService serviceOfRacerDataSet;
    private FormattedTableOfRacers formatter;
    FormattedTableOfRacersTest(){
        MockitoAnnotations.initMocks(this);
        formatter = new FormattedTableOfRacers(serviceOfRacerDataSet);
    }
    @Test
    void makeTableOfRacers() throws IOException, URISyntaxException {

        given(serviceOfRacerDataSet.makeRacersTable()).willReturn(Arrays.asList(
                new RacerData("Brendon Hartley","SCUDERIA TORO ROSSO HONDA","01:13.179"),
                new RacerData("Marcus Ericsson","SAUBER FERRARI","01:13.265")));
        String expected = " 1. Brendon Hartley     |SCUDERIA TORO ROSSO HONDA |01:13.179\n"
                + " 2. Marcus Ericsson     |SAUBER FERRARI            |01:13.265\n"
                + "-------------------------------------------------------------\n";
        assertEquals(expected,formatter.makeTableOfRacers());
    }
}