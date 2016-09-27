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
        n = new Node(3,5);
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of lisaaNaapuri method, of class Node.
     */
    @Test
    public void testLisaaNaapuri() {
        assertTrue("Uudella nodella ei pitäisi olla yhtään naapuria", n.getNaapurit().size() == 0);
        Node naapuri = new Node(4, 3);
        n.lisaaNaapuri(naapuri);
        assertTrue("Yhden naapurin lisäämisen jälkeen nodella pitäisi olla yksi naapuri", n.getNaapurit().size() == 1);
    }

    /**
     * Test of setEdeltaja method, of class Node.
     */
    @Test
    public void testSetEdeltaja() {
        assertTrue("Uuden noden edeltäjän pitäisi olla null", n.getEdeltaja() == null);
        Node edeltaja = new Node(6, 3);
        edeltaja.nollaaG();
        n.yritaAsettajaaEdeltaja(edeltaja);
        assertTrue("Edeltäjän asettaminen toimii väärin", n.getEdeltaja() == edeltaja);
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
     * Test of getG method, of class Node.
     */
    @Test
    public void testGetG() {
        assertEquals("Uudelle nodelle kutsuttaessa getG-metodin pitäisi palauttaa "
                + "suurin sallittu double", Double.MAX_VALUE, n.getG(), 0.000001);
        Node edeltaja = new Node(3, 6);
        edeltaja.nollaaG();
        n.yritaAsettajaaEdeltaja(edeltaja);
        assertTrue("Kun nodella on yksi edeltäjä, getG-metodin pitäisi palauttaa 1", n.getG()==1);
        Node n2 = new Node(4, 6);
        n2.yritaAsettajaaEdeltaja(n);
        assertTrue("Kun nodella on yksi edeltäjä, jolla on edletäjä, getG-metodin"
                + " pitäisi palauttaa 2", n2.getG()==2);
    }

    /**
     * Test of f method, of class Node.
     */
    @Test
    public void testF() {
        Node maali = new Node(10, 10);
        Node edeltaja = new Node(3, 6);
        edeltaja.nollaaG();
        n.yritaAsettajaaEdeltaja(edeltaja);
        System.out.println(n.getG());
        Node n2 = new Node(4, 6);
        n2.yritaAsettajaaEdeltaja(n);
        assertEquals(12, n2.f(maali), 0.001);
    }
    
}
