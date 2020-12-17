package com.foxminded;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Main {
    public static void main(String[] args) throws IOException,NullPointerException{
        Path start = Paths.get("/home/gleb/foxmindedpr03/src/main/resources/start.log");
        Path end = Paths.get("/home/gleb/foxmindedpr03/src/main/resources/end.log");
        Path abbreviations = Paths.get("/home/gleb/foxmindedpr03/src/main/resources/abbreviations.txt");
        TableOfRacers table = new TableOfRacers(start,end,abbreviations);
        System.out.println(table.makeRacersTable());
    }
}
