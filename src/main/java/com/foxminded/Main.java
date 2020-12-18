package com.foxminded;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException,NullPointerException{
        RacersFile impl = new RacersFileDao();
        FormattedTableOfRacers table = new FormattedTableOfRacers(impl);
        System.out.println(table.makeTableOfRacers());
    }
}
