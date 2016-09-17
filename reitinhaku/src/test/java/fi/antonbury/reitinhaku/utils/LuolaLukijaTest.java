/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fi.antonbury.reitinhaku.utils;

import fi.antonbury.reitinhaku.utils.LuolaLukija;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author anni
 */
public class LuolaLukijaTest {

    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final ByteArrayOutputStream errContent = new ByteArrayOutputStream();

    public LuolaLukijaTest() {
    }

    @Before
    public void setUpStreams() {
        System.setOut(new PrintStream(outContent));
        System.setErr(new PrintStream(errContent));
    }

    @After
    public void cleanUpStreams() {
        System.setOut(null);
        System.setErr(null);
    }

    @Test
    public void testLueToimivaLuola() {
        boolean[][] luola1 = null;
        try {
            luola1 = LuolaLukija.lueLuola("resources/testiluolat/testiluola1.txt");
        } catch (IOException ex) {
            fail("Odottamaton poikkeus:\n" + ex.getMessage());
        }
        boolean[][] odotettuLuola1 = {{true, false, true, true},
        {true, true, true, false}};
        assertTrue("Luetun luolan piti olla\n" + odotettuLuola1.toString()
                + "\noli\n" + luola1.toString(), vertaaBooleanArrayt(luola1, odotettuLuola1));
    }

    @Test
    public void testLiianVahanRiveja() {
        boolean[][] luola = null;
        try {
            luola = LuolaLukija.lueLuola("resources/testiluolat/liianVahanRiveja.txt");
        } catch (IOException ex) {
            fail("Odottamaton poikkeus:\n" + ex.getMessage());
        }
        boolean[][] odotettuLuola = {{true, true}, {false, false}};
        assertTrue("Kun luolatiedostossa on oletettua vähemmän rivejä, pitäisi "
                + "loppuosan luolasta olla ei-kuljettavaa. Odotettu luola:\n"
                + odotettuLuola.toString() + "\nTodellinen:\n"
                + luola.toString(), vertaaBooleanArrayt(luola, odotettuLuola));
    }

    @Test
    public void testaaLiianVahanSarakkeita() {
        try {
            boolean[][] b = LuolaLukija.lueLuola("resources/testiluolat/liianVahanSarakkeita.txt");
            fail("Odotettiin poikkeusta kun luolassa on liian vähän sarakkeita.");
        } catch (IOException e) {
        }
    }

    @Test
    public void testaaLiikaaRiveja() {
        try {
            boolean[][] b = LuolaLukija.lueLuola("resources/testiluolat/liikaaRiveja.txt");
            fail("Odotettiin poikkeusta kun luolassa on liikaa rivejä.");
        } catch (ArrayIndexOutOfBoundsException e) {
        } catch (Exception e) {
            fail("Odottamaton poikkeus kun luolatiedostossa on liikaa rivejä.");
        }
    }

    @Test
    public void testaaLiikaaSarakkeita() {
        try {
            boolean[][] b = LuolaLukija.lueLuola("resources/testiluolat/liikaaSarakkeita.txt");
            fail("Odotettiin poikkeusta kun luolassa on liikaa sarakkeita.");
        } catch (IOException e){
        }
    }

    @Test
    public void testaaNaNArvo() {
        try {
            boolean[][] b = LuolaLukija.lueLuola("resources/testiluolat/nanArvoja.txt");
            fail("Odotettiin poikkeusta kun luolassa on ei-kokonaislukuarvoja.");
        } catch (IOException e) {
            fail("Odottamaton IOException kun luolassa on ei-kokonaislukuarvoja.");
        } catch (NumberFormatException e) {
            assertTrue(errContent.toString().contains("sisälsi arvon jota ei voinut parsia kokonaisluvuksi"));
        }
    }

    @Test
    public void testaaOlematonTiedosto() {
        try {
            boolean[][] b = LuolaLukija.lueLuola("resources/testiluolat/eiOikeaTiedosto.txt");
            fail("Odotettiin poikkeusta kun yritetään lukea luola tiedostosta, jota ei ole olemassa.");
        } catch (IOException e) {
        }
    }

    @Test
    public void testaaVirheellinenHeader() {
        try {
            boolean[][] b = LuolaLukija.lueLuola("resources/testiluolat/lyhytHeader.txt");
            fail("Odotettiin poikkeusta kun yritetään lukea luola tiedostosta, jonka headerissa ei ole kahta lukua.");
        } catch (IOException e) {
            fail("Odottamaton IOException luettaessa luolaa tiedostosta, jonka headerissa ei ole kahta lukua.");
        } catch (IndexOutOfBoundsException e) {
        }

        try {
            boolean[][] b = LuolaLukija.lueLuola("resources/testiluolat/nanHeader.txt");
            fail("Odotettiin poikkeusta kun yritetään lukea luola tiedostosta, jonka headerissa on muuta kuin kokonaislukuja.");
        } catch (IOException e) {
            fail("Odottamaton IOException luettaessa luolaa tiedostosta, jonka headerissa on muuta kuin kokonaislukuja.");
        } catch (NumberFormatException e) {
            assertTrue(errContent.toString().contains("Luolatiedoston ensimmäisen rivin tulee sisältää kaksi välilyönneillä erotettua lukua, joista ensimmäinen kertoo luolataulukon rivien määrän"));
        }
    }

    private boolean vertaaBooleanArrayt(boolean[][] array1, boolean[][] array2) {
        if (array1.length != array2.length || array1[0].length != array2[0].length) {
            return false;
        }

        for (int i = 0; i < array1.length; i++) {
            for (int j = 0; j < array1[0].length; j++) {
                if (array1[i][j] != array2[i][j]) {
                    return false;
                }
            }
        }

        return true;
    }
}
