package com.racer.formatter;

import com.racer.dataset.RacersFile;
import com.racer.dataset.RacersFileDao;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.net.URISyntaxException;

import static org.junit.jupiter.api.Assertions.*;

class FormattedTableOfRacersTest {
    private RacersFile impl = new RacersFileDao();
    private FormattedTableOfRacers test = new FormattedTableOfRacers
            (impl,"testAbbreviations.txt","testStart.log","testEnd.log");

    @Test
    void makeTableOfRacers() throws IOException, URISyntaxException {
        String expected = " 1. Brendon Hartley     |SCUDERIA TORO ROSSO HONDA |01:13.179\n"
                + " 2. Marcus Ericsson     |SAUBER FERRARI            |01:13.265\n"
                + "-------------------------------------------------------------\n";
        assertEquals(expected,test.makeTableOfRacers());
    }
}