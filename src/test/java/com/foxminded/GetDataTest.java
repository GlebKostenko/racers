package com.foxminded;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

class GetDataTest {
    private Path start = Paths.get("/home/gleb/foxmindedpr03/src/main/resources/start.log");
    private Path end = Paths.get("/home/gleb/foxmindedpr03/src/main/resources/end.log");
    private Path abbreviations = Paths.get("/home/gleb/foxmindedpr03/src/main/resources/abbreviations.txt");
    private GetData test = new GetData(start,end,abbreviations);

    @Test
    void makeStartDataSet() throws IOException{
        List<String> expected = new ArrayList<>();
        expected.add("SVF2018-05-24_12:02:58.917");
        expected.add("NHR2018-05-24_12:02:49.914");
        expected.add("FAM2018-05-24_12:13:04.512");
        expected.add("KRF2018-05-24_12:03:01.250");
        expected.add("SVM2018-05-24_12:18:37.735");
        expected.add("MES2018-05-24_12:04:45.513");
        expected.add("LSW2018-05-24_12:06:13.511");
        expected.add("BHS2018-05-24_12:14:51.985");
        expected.add("EOF2018-05-24_12:17:58.810");
        expected.add("RGH2018-05-24_12:05:14.511");
        expected.add("SSW2018-05-24_12:16:11.648");
        expected.add("KMH2018-05-24_12:02:51.003");
        expected.add("PGS2018-05-24_12:07:23.645");
        expected.add("CSR2018-05-24_12:03:15.145");
        expected.add("SPF2018-05-24_12:12:01.035");
        expected.add("DRR2018-05-24_12:14:12.054");
        expected.add("LHM2018-05-24_12:18:20.125");
        expected.add("CLS2018-05-24_12:09:41.921");
        expected.add("VBM2018-05-24_12:00:00.000");

        expected = expected.stream().sorted().collect(Collectors.toList());
        assertEquals(expected,test.makeStartDataSet());
    }

    @Test
    void makeEndDataSet() throws IOException {
        List<String> expected = new ArrayList<>();
        expected.add("MES2018-05-24_12:05:58.778");
        expected.add("RGH2018-05-24_12:06:27.441");
        expected.add("SPF2018-05-24_12:13:13.883");
        expected.add("LSW2018-05-24_12:07:26.834");
        expected.add("DRR2018-05-24_12:15:24.067");
        expected.add("NHR2018-05-24_12:04:02.979");
        expected.add("CSR2018-05-24_12:04:28.095");
        expected.add("KMH2018-05-24_12:04:04.396");
        expected.add("BHS2018-05-24_12:16:05.164");
        expected.add("SVM2018-05-24_12:19:50.198");
        expected.add("KRF2018-05-24_12:04:13.889");
        expected.add("VBM2018-05-24_12:01:12.434");
        expected.add("SVF2018-05-24_12:04:03.332");
        expected.add("EOF2018-05-24_12:19:11.838");
        expected.add("PGS2018-05-24_12:08:36.586");
        expected.add("SSW2018-05-24_12:17:24.354");
        expected.add("FAM2018-05-24_12:14:17.169");
        expected.add("CLS2018-05-24_12:10:54.750");
        expected.add("LHM2018-05-24_12:19:32.585");

        expected = expected.stream().sorted().collect(Collectors.toList());
        assertEquals(expected,test.makeEndDataSet());
    }

    @Test
    void makeAbbreviationsDataSet() throws IOException{
        List<String> expected = new ArrayList<>();
        expected.add("DRR_Daniel Ricciardo_RED BULL RACING TAG HEUER");
        expected.add("SVF_Sebastian Vettel_FERRARI");
        expected.add("LHM_Lewis Hamilton_MERCEDES");
        expected.add("KRF_Kimi Raikkonen_FERRARI");
        expected.add("VBM_Valtteri Bottas_MERCEDES");
        expected.add("EOF_Esteban Ocon_FORCE INDIA MERCEDES");
        expected.add("FAM_Fernando Alonso_MCLAREN RENAULT");
        expected.add("CSR_Carlos Sainz_RENAULT");
        expected.add("SPF_Sergio Perez_FORCE INDIA MERCEDES");
        expected.add("PGS_Pierre Gasly_SCUDERIA TORO ROSSO HONDA");
        expected.add("NHR_Nico Hulkenberg_RENAULT");
        expected.add("SVM_Stoffel Vandoorne_MCLAREN RENAULT");
        expected.add("SSW_Sergey Sirotkin_WILLIAMS MERCEDES");
        expected.add("CLS_Charles Leclerc_SAUBER FERRARI");
        expected.add("RGH_Romain Grosjean_HAAS FERRARI");
        expected.add("BHS_Brendon Hartley_SCUDERIA TORO ROSSO HONDA");
        expected.add("MES_Marcus Ericsson_SAUBER FERRARI");
        expected.add("LSW_Lance Stroll_WILLIAMS MERCEDES");
        expected.add("KMH_Kevin Magnussen_HAAS FERRARI");

        expected = expected.stream().sorted().collect(Collectors.toList());
        assertEquals(expected,test.makeAbbreviationsDataSet());
    }
}