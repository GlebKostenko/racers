package com.foxminded;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class WorkWithDataSetTest {
private WorkWithDataSet impl = new WorkWithDataSet();

    @Test
    void parseAbbreviations() {
        List<String> test = new ArrayList<>();
        test.add("DRR_Daniel Ricciardo_RED BULL RACING TAG HEUER");
        test.add("SVF_Sebastian Vettel_FERRARI");
        HashMap<String,String> expected = new HashMap<>();
        expected.put("DRR","Daniel Ricciardo    |RED BULL RACING TAG HEUER ");
        expected.put("SVF","Sebastian Vettel    |FERRARI                   ");

        assertEquals(expected,impl.parseAbbreviations(test));
    }

    @Test
    void calculateTopTimeForRacer() {
        String expected = "01:04.415";
        assertEquals(expected,impl.calculateTopTimeForRacer("12:02:58.917","12:04:03.332"));
    }

    @Test
    void getStartOrEndTimeFromString() {
        String expected = "12:04:03.332";
        assertEquals(expected,impl.getStartOrEndTimeFromString("SVF2018-05-24_12:04:03.332"));
    }
}