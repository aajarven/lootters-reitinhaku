/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fi.antonbury.reitinhaku.utils;

import fi.antonbury.reitinhaku.haku.Node;
import java.util.logging.Logger;
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
public class PrioriteettijonoTest {
    
    private Node maali;
    private Prioriteettijono jono;
    
    public PrioriteettijonoTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        maali = new Node(10, 10);
        jono = new Prioriteettijono(maali);
        
    }
    
    @After
    public void tearDown() {
    }

    @Test
    public void testLisaaJaPoimi() {
        Node n = new Node(1, 2);
        jono.lisaaNode(n);
        assertEquals("Kun jonoon laitetaan yksi alkio ja sen jälkeen kutsutaan"
                + " poimiNode-metodia, pitäisi jonon palauttaa äsken lisätty"
                + " node", n, jono.poimiNode());
    }

    @Test
    public void testPoimintaJarjestys() {
        Node n1 = new Node(0, 0);
        Node n2 = new Node(0, 1);
        Node n3 = new Node(0, 2);
        Node n4 = new Node(1, 2);
        Node n5 = new Node(1, 3);
        Node n6 = new Node(0, 3);
        Node n7 = new Node(0, 4);
        
        n1.nollaaKustannusNodeenAsti();
        n2.yritaAsettajaaEdeltaja(n1);
        n3.yritaAsettajaaEdeltaja(n2);
        n4.yritaAsettajaaEdeltaja(n3);
        n5.yritaAsettajaaEdeltaja(n4);
        n6.yritaAsettajaaEdeltaja(n5);
        n7.yritaAsettajaaEdeltaja(n6);
        
        jono.lisaaNode(n1);
        jono.lisaaNode(n2);
        jono.lisaaNode(n3);
        jono.lisaaNode(n4);
        jono.lisaaNode(n5);
        jono.lisaaNode(n6);
        jono.lisaaNode(n7);
        
        Node edellinen = jono.poimiNode();
        int poimitut = 1;
        while (!jono.onTyhja()){
            Node seuraava = jono.poimiNode();
            assertTrue("Kun jonosta poimitaan alkioita, pitäisi seuraavan alkion "
                    + "f-metodin aina palauttaa vähintään yhtä suuri luku "
                    + "kuin edellisen", seuraava.kokonaiskustannus(maali) >= edellinen.kokonaiskustannus(maali));
            edellinen = seuraava;
            poimitut++;
        }
        assertEquals("Kun jonoon on lisätty seitsemän Nodea ja se poimitaan "
                + "tyhjäksi, pitäisi poimittuja alkioita olla seitsemän",
                7, poimitut);
    }

    /**
     * Test of onTyhja method, of class NodeJono.
     */
    @Test
    public void testOnTyhja() {
        assertTrue("Vastaluodun jonon onTyhja-metodin pitäisi palauttaa true", jono.onTyhja());
        jono.lisaaNode(new Node(1, 2));
        assertFalse("Jonon, johon on lisätty yksi alkio, onTyhja-metodin pitäisi palauttaa false", jono.onTyhja());
        jono.poimiNode();
        assertTrue("Jonon, jonka kaikki alkiot on poistettu, onTyhja-metodin pitäisi palauttaa true", jono.onTyhja());
    }
    
    @Test
    public void testEnsimmaisenVaihtaminenOnnistuu(){
        Node n1 = new Node(1, 0);
        Node n2 = new Node(0, 1);
        n1.nollaaKustannusNodeenAsti();
        
        jono.lisaaNode(n2);
        jono.lisaaNode(n1);
        assertEquals("Kun jonoon lisätään esin Node jolla on suuri f ja sen "
                + "jälkeen node jolla on pieni f, pitäisi jonon palauttaa "
                + "jälkimmäinen alkio ensin", n1, jono.poimiNode());
    }
    

    @Test
    public void testDuplikaattienLisaaminen(){
        Node n1 = new Node(1, 0);
        jono.lisaaNode(n1);
        jono.lisaaNode(n1);
        jono.lisaaNode(n1);
        jono.lisaaNode(n1);
        jono.poimiNode();
        assertTrue("Jos jonoon lisätään ainoastaan sama Node monesti, pitäisi "
                + "jonon olla yhden poiminnan jälkeen tyhjä.", jono.onTyhja());
        
        Node n2 = new Node(2, 0);
        n2.nollaaKustannusNodeenAsti();
        jono.lisaaNode(n2);
        jono.lisaaNode(n1);
        jono.lisaaNode(n2);
        jono.lisaaNode(n2);
        jono.lisaaNode(n1);
        jono.poimiNode();
        jono.poimiNode();
        assertTrue("Jos jonoon lisätään ainoastaan kahta alkiota monesti, pitäisi "
                + "jonon olla kahden poiminnan jälkeen tyhjä.", jono.onTyhja());
    }
    
//    @Test
//    public void testPituus(){
//        Node n1 = new Node(1, 0);
//        Node n2 = new Node(3, 6);
//        Node n3 = new Node(5, 2);
//        
//        assertEquals("Tyhjän jonon pituuden pitäisi olla 0", 0, jono.pituus());
//        jono.lisaaNode(n1);
//        assertEquals("Yhden noden lisäämisen jälkeen jonon pituuden pitäisi olla"
//                + " 1", 1, jono.pituus());
//        jono.lisaaNode(n1);
//        assertEquals("Kahden saman Node-olion lisäämisen jälkeen jonon pituuden "
//                + "pitäisi olla 1", 1, jono.pituus());
//        
//        jono.lisaaNode(n2);
//        assertEquals("Kahden uniikin Noden lisäämisen jälkeen jonon pituuden "
//                + "pitäisi olla 2", 2, jono.pituus());
//        
//        jono.lisaaNode(n2);
//        jono.lisaaNode(n3);
//        assertEquals("Kolmen uniikin Noden lisäämisen jälkeen jonon pituuden "
//                + "pitäisi olla 3", 3, jono.pituus());
//    }
    
}
