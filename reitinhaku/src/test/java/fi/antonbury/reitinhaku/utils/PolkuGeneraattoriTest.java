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
public class PolkuGeneraattoriTest {
    
    public PolkuGeneraattoriTest() {
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
     * Test of generoiPolku method, of class PolkuGeneraattori.
     */
    @Test
    public void testGeneroiPolku() {
        Node n1 = new Node(0,0);
        Node n2 = new Node(1,0);
        Node n3 = new Node(2, 0);
        Node n4 = new Node(2, 1);
        Node n5 = new Node(3, 1);
        Node n6 = new Node(3, 2);
        Node n7 = new Node(3, 3);
        Node hamy = new Node(2, 2);
        
        n1.nollaaKustannusNodeenAsti();
        n2.yritaAsettajaaEdeltaja(n1);
        n3.yritaAsettajaaEdeltaja(n2);
        n4.yritaAsettajaaEdeltaja(n3);
        n5.yritaAsettajaaEdeltaja(n4);
        n6.yritaAsettajaaEdeltaja(n5);
        n7.yritaAsettajaaEdeltaja(n6);
        hamy.yritaAsettajaaEdeltaja(n4);
        
        Node[] polku = PolkuGeneraattori.generoiPolku(n7);
        
        assertEquals("Generoidun polun pituus oli väärä", 7, polku.length);
        assertArrayEquals("Generoidulla polulla oli väärät nodet", new Node[]{n1, n2, n3, n4, n5, n6, n7} , polku);
    }
    
}
