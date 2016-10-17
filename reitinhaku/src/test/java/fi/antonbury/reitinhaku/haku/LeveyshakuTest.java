/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fi.antonbury.reitinhaku.haku;

import fi.antonbury.reitinhaku.utils.LuolaLukija;
import fi.antonbury.reitinhaku.utils.NodeGeneraattori;
import java.io.IOException;
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
public class LeveyshakuTest {
    
    Node[][] luola;
    
    public LeveyshakuTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of BFS method, of class Leveyshaku.
     */
    @Test
    public void testHae() {
        boolean[][] luolaboolean = null;
        try {
            luolaboolean = LuolaLukija.lueLuola("resources/testiluolat/hakuluola1.txt");
        } catch (IOException ex) {
            fail("Odottamaton poikkeus:\n" + ex.getMessage());
        }
        luola = NodeGeneraattori.generoiNodet(luolaboolean);
        
        Node[] maali = AStar.hae(luola[0][0], luola[9][9]);
        
        luola = NodeGeneraattori.generoiNodet(luolaboolean);
        Node[] polku = Leveyshaku.hae(luola[6][2], maali);
        
        assertTrue("Polun ei pitäisi olla null kun sellainen on olemassa", polku!=null);
        assertEquals("Polun pituus on väärä", 7, polku.length);
        assertEquals("Polun ensimmäisen alkion pitäisi olla lähtöpaikka", luola[6][2], polku[0]);
        assertEquals("Polun viimeisen alkion pitäisi olla maalisolmu", luola[2][4], polku[polku.length-1]);
        assertEquals("Polun keskellä oli väärä alkio", luola[5][4], polku[3]);
    }
    
    @Test
    public void testTaytyyOdottaa(){
        boolean[][] luolaboolean = null;
        try {
            luolaboolean = LuolaLukija.lueLuola("resources/testiluolat/hakuluola1.txt");
        } catch (IOException ex) {
            fail("Odottamaton poikkeus:\n" + ex.getMessage());
        }
        luola = NodeGeneraattori.generoiNodet(luolaboolean);
        
        Node[] maali = AStar.hae(luola[0][0], luola[9][9]);
        
        luola = NodeGeneraattori.generoiNodet(luolaboolean);
        Node[] polku = Leveyshaku.hae(luola[9][2], maali);
        
        assertTrue("Polun ei pitäisi olla null kun sellainen on olemassa", polku!=null);
        assertEquals("Polun pituus on väärä", 13, polku.length);
        assertEquals("Polun ensimmäisen alkion pitäisi olla lähtöpaikka", luola[9][2], polku[0]);
        assertEquals("Polun viimeisen alkion pitäisi olla solmu, johon kannattaa jäädä odottamaan", luola[4][9], polku[polku.length-1]);
    }
    
    @Test
    public void testEiPolkua(){
        boolean[][] luolaboolean = null;
        try {
            luolaboolean = LuolaLukija.lueLuola("resources/testiluolat/hakuluola1.txt");
        } catch (IOException ex) {
            fail("Odottamaton poikkeus:\n" + ex.getMessage());
        }
        luola = NodeGeneraattori.generoiNodet(luolaboolean);
        
        Node[] maali = AStar.hae(luola[0][0], luola[1][4]);
        
        luola = NodeGeneraattori.generoiNodet(luolaboolean);
        Node[] polku = Leveyshaku.hae(luola[6][2], maali);
        
        assertTrue("Leveyshaun pitäisi palauttaa null kun polkua ei löydy", null==polku);
    }
    
    @Test
    public void testTakaaAjo(){
        boolean[][] luolaboolean = null;
        try {
            luolaboolean = LuolaLukija.lueLuola("resources/testiluolat/hakuluola1.txt");
        } catch (IOException ex) {
            fail("Odottamaton poikkeus:\n" + ex.getMessage());
        }
        luola = NodeGeneraattori.generoiNodet(luolaboolean);
        
        Node[] maali = AStar.hae(luola[1][2], luola[9][9]);
        
        luola = NodeGeneraattori.generoiNodet(luolaboolean);
        Node[] polku = Leveyshaku.hae(luola[1][1], maali);
        
        assertTrue("Kun jahdattavaa ei voida saada kiinni, pitäisi leveyshaun "
                + "palauttaa null", polku==null);
    }
    
}
