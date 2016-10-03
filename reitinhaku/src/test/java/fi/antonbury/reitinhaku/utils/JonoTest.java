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
public class JonoTest {
    
    Jono jono;
    
    public JonoTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        jono = new Jono();
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of lisaaAlkio method, of class Jono.
     */
    @Test
    public void testLisaaAlkio() {
        jono.lisaaAlkio(new Node(1,2));
        assertFalse("Jonon, johon on lisätty alkio, ei pitäisi olla tyhjä", jono.onTyhja());
    }

    /**
     * Test of poimiNode method, of class Jono.
     */
    @Test
    public void testPoimiNode() {
        Node n1 = new Node(1, 3);
        Node n2 = new Node(3, 5);
        Node n3 = new Node(5, 2);
        
        jono.lisaaAlkio(n1);
        jono.lisaaAlkio(n2);
        jono.lisaaAlkio(n3);
        
        assertEquals("Jonoon ensimmäisenä lisätyn alkion pitäisi tulla "
                + "ensimmäisenä pois", n1, jono.poimiNode());
        assertEquals("Jonoon toisena lisätyn alkion pitäisi tulla "
                + "toisena pois", n2, jono.poimiNode());
        assertEquals("Jonoon kolmantena lisätyn alkion pitäisi tulla "
                + "kolmantena pois", n3, jono.poimiNode());
    }

    /**
     * Test of onTyhja method, of class Jono.
     */
    @Test
    public void testOnTyhja() {
        assertTrue("Vastaluodun jonon pitäisi olla tyhjä", jono.onTyhja());
        jono.lisaaAlkio(new Node(1, 4));
        assertFalse("Jonon, johon on lisätty yksi alkio, ei pitäisi olla tyhjä",
                jono.onTyhja());
        jono.poimiNode();
        assertTrue("Jonon, jonka ainoa alkio on poimittu, pitäisi olla tyhjä",
                jono.onTyhja());
    }
    
    /**
     * Testaa tyhjästä jonosta poimimista
     */
    @Test
    public void testPoimiTyhjastaJonosta(){
        assertEquals("Tyhjälle jonolle kutsutun poimiNode-metodin pitäisi "
                + "palauttaa null", null, jono.poimiNode());
    }
}
