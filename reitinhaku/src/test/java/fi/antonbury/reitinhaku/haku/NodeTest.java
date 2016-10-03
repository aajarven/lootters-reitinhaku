/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fi.antonbury.reitinhaku.haku;

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
public class NodeTest {

    Node n;

    public NodeTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        n = new Node(3, 5);
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of lisaaNaapuri method, of class Node.
     */
    @Test
    public void testLisaaNaapuri() {
        assertTrue("Uudella nodella ei pitäisi olla yhtään naapuria", n.getNaapurit().length == 0);
        Node naapuri = new Node(4, 3);
        n.lisaaNaapuri(naapuri);
        assertTrue("Yhden naapurin lisäämisen jälkeen nodella pitäisi olla yksi naapuri", n.getNaapurit().length == 1);
    }

    /**
     * Test of getX method, of class Node.
     */
    @Test
    public void testGetX() {
        assertTrue("getX-metodi palautti väärän arvon", n.getX() == 3);
    }

    /**
     * Test of getY method, of class Node.
     */
    @Test
    public void testGetY() {
        assertTrue("getY-metodi palautti väärän arvon", n.getY() == 5);
    }

    /**
     * Test of getKustannusNodeenAsti method, of class Node.
     */
    @Test
    public void testGetKustannusNodeenAsti() {
        assertEquals("Uudelle nodelle kutsuttaessa getG-metodin pitäisi palauttaa "
                + "suurin sallittu int", Integer.MAX_VALUE, n.getKustannusNodeenAsti());
        Node edeltaja = new Node(3, 6);
        edeltaja.nollaaKustannusNodeenAsti();
        n.yritaAsettajaaEdeltaja(edeltaja);
        assertTrue("Kun nodella on yksi edeltäjä, getG-metodin pitäisi palauttaa 1", n.getKustannusNodeenAsti() == 1);
        Node n2 = new Node(4, 6);
        n2.yritaAsettajaaEdeltaja(n);
        assertTrue("Kun nodella on yksi edeltäjä, jolla on edletäjä, getG-metodin"
                + " pitäisi palauttaa 2", n2.getKustannusNodeenAsti() == 2);
    }

    /**
     * Test of kokonaiskustannus method, of class Node.
     */
    @Test
    public void testḰokonaiskustannus() {
        Node maali = new Node(10, 10);
        Node edeltaja = new Node(3, 6);
        edeltaja.nollaaKustannusNodeenAsti();
        n.yritaAsettajaaEdeltaja(edeltaja);
        System.out.println(n.getKustannusNodeenAsti());
        Node n2 = new Node(4, 6);
        n2.yritaAsettajaaEdeltaja(n);
        assertEquals(12, n2.kokonaiskustannus(maali), 0.001);
    }

    /**
     * Test of yritaAsettajaaEdeltaja method, of class Node
     */
    @Test
    public void testYritaAsettajaaEdeltaja() {
        Node alku = new Node(0, 0);
        alku.nollaaKustannusNodeenAsti();
        Node va = new Node(0, 1); // vasen ala
        Node oy = new Node(1, 0); // oikea ylä
        Node oa = new Node(1, 1); // oikea ala
        
        assertTrue("Noden, jolla ei vielä ole edeltäjää, pitäisi suostua"
                + "ottamaan alkunode edeltäjäkseen",
                va.yritaAsettajaaEdeltaja(alku));
        assertTrue("Noden, jolla ei vielä ole edeltäjää, pitäisi suostua"
                + "ottamaan alkunode edeltäjäkseen",
                oy.yritaAsettajaaEdeltaja(alku));
        assertTrue("Noden, jolla ei vielä ole edeltäjää, pitäisi suostua "
                + "ottamaan node, jolla on edeltäjä, edeltäjäkseen", 
                oa.yritaAsettajaaEdeltaja(va));
        assertFalse("Noden, jolla on jo parempi edeltäjä, ei pitäisi vaihtaa"
                + " huonompaan", oy.yritaAsettajaaEdeltaja(oa));
        
        alku = new Node(0, 0);
        alku.nollaaKustannusNodeenAsti();
        va = new Node(0, 1); // vasen ala
        oy = new Node(1, 0); // oikea ylä
        oa = new Node(1, 1); // oikea ala
        
        va.yritaAsettajaaEdeltaja(alku);
        oy.yritaAsettajaaEdeltaja(alku);
        oa.yritaAsettajaaEdeltaja(va);
        assertFalse("Noden ei pitäisi vaihtaa edeltäjää toiseen yhtä hyvään",
                oa.yritaAsettajaaEdeltaja(oy));
    }
    
    @Test
    public void testEquals(){
        Node n1 = new Node(4, 8);
        Node n2 = new Node(4, 8);
        Node n3 = new Node(5, 7);
        Object o = new Object();
        
        assertEquals("Kahden noden, joilla on samat koordinaatit, pitäisi olla samat", n1, n2);
        assertNotEquals("Kahden noden, joilla on eri koordinaatit, ei pitäisi olla samat", n1, n3);
        assertNotEquals("Noden ja olion, joka ei ole node, ei pitäisi olla samat", n1, o);
    }
    
    @Test
    public void testHashCode(){
        Node n1 = new Node(4, 8);
        Node n2 = new Node(4, 8);
        
        assertEquals("Kahden noden, joilla on samat koordinaatit, HashCodejen pitäisi olla samat", n1.hashCode(), n2.hashCode());
    }

}
