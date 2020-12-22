package com.foxminded;

import java.io.IOException;
import java.net.URISyntaxException;

import com.RacerDataSet.*;
import com.FormatTheResultOfWorking.*;
import com.racer.dataset.RacersFile;
import com.racer.dataset.RacersFileDao;
import com.racer.formatter.FormattedTableOfRacers;

public class Main {
    public static void main(String[] args) throws IOException,NullPointerException ,URISyntaxException{
        RacersFile impl = new RacersFileDao();
        FormattedTableOfRacers table = new FormattedTableOfRacers(impl,"abbreviations.txt","start.log","end.log");
        System.out.println(table.makeTableOfRacers());
    }
}
