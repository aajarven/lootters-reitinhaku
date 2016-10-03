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
public class JonoAlkioTest {
    
    PrioriteettijonoAlkio a;
    
    public JonoAlkioTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        a = new PrioriteettijonoAlkio(new Node(5, 5));
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of getSeuraaja method, of class JonoAlkio.
     */
    @Test
    public void testSeuraajaToiminnallisuus() {
        assertEquals("Alkion, jolle ei ole asetettu seuraajaa, getSeuraaja-"
                + "metodin pitäisi palauttaa null", null, a.getSeuraaja());
        PrioriteettijonoAlkio seuraaja = new PrioriteettijonoAlkio(new Node(7, 8));
        a.setSeuraaja(seuraaja);
        assertEquals("Asetettaessa alkio toisen seuraajaksi, ei ensimmäisen"
                + "alkion getSeuraaja-metodi palauttanut oikeaa arvoa", seuraaja,
                a.getSeuraaja());
    }

    /**
     * Test of getNode method, of class JonoAlkio.
     */
    @Test
    public void testGetNode() {
        Node n = new Node(5, 6);
        a = new PrioriteettijonoAlkio(n);
        assertEquals("Alkion getNode-metodi palautti eri noden kuin alkiolle"
                + "oli konstruktorissa annettu", n, a.getNode());
    }

    /**
     * Test of kustannus method, of class JonoAlkio.
     */
    @Test
    public void testKustannus() {
        Node alku = new Node(0, 0);
        Node toinen = new Node(0, 1);
        Node maali = new Node(10, 10);
        
        alku.nollaaKustannusNodeenAsti();
        alku.lisaaNaapuri(toinen);
        
        PrioriteettijonoAlkio ekaAlkio = new PrioriteettijonoAlkio(alku);
        assertEquals("Alkion kustannuksen palauttaminen toimi väärin kun noden"
                + " g=0", 20, ekaAlkio.kustannus(maali), 0.001);
        
        toinen.yritaAsettajaaEdeltaja(alku);
        PrioriteettijonoAlkio tokaAlkio = new PrioriteettijonoAlkio(toinen);
        assertEquals("Alkion kustannuksen palauttaminen toimi väärin kun noden "
                + "g != 0", 20, tokaAlkio.kustannus(maali), 0.001);
    }
    
}
