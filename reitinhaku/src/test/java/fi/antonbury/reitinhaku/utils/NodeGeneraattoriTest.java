/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fi.antonbury.reitinhaku.utils;

import fi.antonbury.reitinhaku.haku.Node;
import java.io.IOException;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import static org.junit.Assert.*;

/**
 *
 * @author anni
 */
public class NodeGeneraattoriTest {
    
    Node[][] luola;
    
    public NodeGeneraattoriTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        boolean[][]  luolaboolean = null;
        try {
            luolaboolean = LuolaLukija.lueLuola("resources/testiluolat/testiluola2.txt");
        } catch (IOException ex) {
            fail("Odottamaton poikkeus:\n" + ex.getMessage());
        }
        luola = NodeGeneraattori.generoiNodet(luolaboolean);
    }
    
    @After
    public void tearDown() {
    }

    @org.junit.Test
    public void testNodejaOikeaMaara() {
        assertEquals("Luolassa oli väärä määrä sarakkeita", 7, luola.length);
        assertEquals("Luolassa oli väärä määrä rivejä", 4, luola[0].length);
    }
    
    @org.junit.Test
    public void testNaapurimaarat(){
        assertEquals("Testiluolan vasemman yläkulman nodella oli väärä määrä naapureita", 1, luola[0][0].getNaapurit().size());
        assertEquals("Testiluolan vasemman alakulman nodella oli väärä määrä naapureita", 1, luola[6][0].getNaapurit().size());
        assertEquals("Testiluolan oikean yläkulman nodella oli väärä määrä naapureita", 1, luola[0][3].getNaapurit().size());
        assertEquals("Nodella, jolla pitäisi olla neljä naapuria, oli väärä määrä naapureita", 4, luola[2][2].getNaapurit().size());
        assertEquals("Nodella, jolla pitäisi olla kaksi naapuria, oli väärä määrä naapureita", 2, luola[0][2].getNaapurit().size());
        assertEquals("Nodella, jolla pitäisi olla kolme naapuria, oli väärä määrä naapureita", 3, luola[5][2].getNaapurit().size());
    }
    
    @org.junit.Test
    public void testEiNodejaNulleista(){
        assertEquals("Kulkukelvottomasta ruudusta ei pitäisi luoda nodea", null, luola[6][3]);
    }
    
    @org.junit.Test
    public void testNaapuritOikein(){
        assertEquals("Noden naapurin naapurin pitäisi olla node itse kun naapurilla on vain yksi naapuri", luola[3][0], luola[3][0].getNaapurit().get(0).getNaapurit().get(0));
    }
}
