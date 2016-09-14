/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fi.antonbury.reitinhaku.utils;

import fi.antonbury.reitinhaku.utils.LuolaLukija;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author anni
 */
public class LuolaLukijaTest {

    public LuolaLukijaTest() {
    }
    
    @Test
    public void testLueToimivaLuola(){
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
    public void testLiianVahanRiveja(){
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
    public void testaaLiianVahanSarakkeita(){
        try {
            boolean[][] b = LuolaLukija.lueLuola("resources/testiluolat/liianVahanSarakkeita.txt");
            fail("Odotettiin poikkeusta kun luolassa on liian vähän sarakkeita.");
        } catch (IOException e){
        }
    }
    
    @Test
    public void testaaLiikaaRiveja(){
        try {
            boolean[][] b = LuolaLukija.lueLuola("resources/testiluolat/liikaaRiveja.txt");
            fail("Odotettiin poikkeusta kun luolassa on liikaa rivejä.");
        } catch (ArrayIndexOutOfBoundsException e){
        } catch (Exception e) {
            fail("Odottamaton poikkeus kun luolatiedostossa on liikaa rivejä.");
        }
    }
    
    @Test
    public void testaaLiikaaSarakkeita(){
        try {
            boolean[][] b = LuolaLukija.lueLuola("resources/testiluolat/liikaaSarakkeita.txt");
            fail("Odotettiin poikkeusta kun luolassa on liikaa sarakkeita.");
        } catch (IOException e){
        }
    }
    
    private boolean vertaaBooleanArrayt(boolean[][] array1, boolean[][] array2){
        if (array1.length != array2.length || array1[0].length != array2[0].length){
            return false;
        }
        
        for (int i = 0; i < array1.length; i++){
            for (int j = 0; j < array1[0].length; j++){
                if (array1[i][j] != array1[i][j]){
                    return false;
                }
            }
        }
        
        return true;
    }
}
