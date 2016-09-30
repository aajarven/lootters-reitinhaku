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
 * @author Anni Järvenpää
 */
public class AStarTest {
    
    Node[][] luola;
    
    public AStarTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        boolean[][] luolaboolean = null;
        try {
            luolaboolean = LuolaLukija.lueLuola("resources/testiluolat/hakuluola1.txt");
        } catch (IOException ex) {
            fail("Odottamaton poikkeus:\n" + ex.getMessage());
        }
        luola = NodeGeneraattori.generoiNodet(luolaboolean);
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of search method, of class AStar.
     */
    @Test
    public void testSearch() {
        Node[] polku = AStar.search(luola[0][0], luola[9][9]);
        
        assertEquals("Polun pituus on väärä", 19, polku.length);
    }
    
}
