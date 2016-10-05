/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fi.antonbury.reitinhaku.utils;

import fi.antonbury.reitinhaku.haku.Node;
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
public class NaapuriListaTest {
    
    NaapuriLista lista;
    
    public NaapuriListaTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        lista = new NaapuriLista();
    }
    
    @After
    public void tearDown() {
    }

    @Test
    public void testLuonti(){
        assertEquals("Uuden naapurilistan pituuden pitäisi olla 0", 0, lista.getNaapurit().length);
    }
    
    /**
     * Test of lisaaNaapuri method, of class NaapuriLista.
     */
    @Test
    public void testLisaaJaPalauta() {
        Node n1 = new Node(1, 2);
        Node n2 = new Node(0, 1);
        Node n3 = new Node(1, 0);
        
        lista.lisaaNaapuri(n1);
        assertEquals("Yhden naapurin lisäämisen jälkeen naapurilistan pituuden"
                + "pitäisi olla 1", 1, lista.getNaapurit().length);
        lista.lisaaNaapuri(n1);
        assertEquals("Duplikaattien lisäämisen ei pitäisi kasvattaa listaa."
                + "Kun yksi node on lisätty listalle kahdesti, pitäisi listan"
                + "pituuden olla yhä 1", 1, lista.getNaapurit().length);
        lista.lisaaNaapuri(n2);
        assertEquals("Kahden uniikin noden lisäämisen jälkeen listan pituuden"
                + "pitäisi olla 2", 2, lista.getNaapurit().length);
        lista.lisaaNaapuri(n3);
        assertEquals("Kolmen uniikin noden lisäämisen jälkeen listan pituuden"
                + "pitäisi olla 3", 3, lista.getNaapurit().length);
        lista.lisaaNaapuri(n2);
        assertEquals("Listalla aiemmin olevan naapurin lisäämisen uudelleen ei"
                + "pitäisi kasvattaa listaa", 3, lista.getNaapurit().length);
    }
    
}
