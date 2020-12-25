package com.foxminded;

import java.io.IOException;
import java.net.URISyntaxException;
import com.racer.dataset.RacersFile;
import com.racer.dataset.RacersFileDao;
import com.racer.formatter.FormattedTableOfRacers;
import com.racer.service.RacersService;

public class Main {
    public static void main(String[] args) throws IOException,NullPointerException ,URISyntaxException{
        RacersFile dataSet = new RacersFileDao("abbreviations.txt","start.log","end.log");
        RacersService impl = new RacersService(dataSet);
        FormattedTableOfRacers table = new FormattedTableOfRacers(impl);
        System.out.println(table.makeTableOfRacers());
    }
}
