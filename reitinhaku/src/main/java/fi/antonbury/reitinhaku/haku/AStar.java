/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fi.antonbury.reitinhaku.haku;

import fi.antonbury.reitinhaku.utils.NodeJono;
import java.util.HashSet;

/**
 *
 * @author anni
 */
public class AStar {

    /**
     * Etsii lyhyimmän polun annetusta lähtönodesta annettuun maalinodeen.
     * Mikäli polku on olemassa, palauttaa polun nodet taulukossa ensimmäisestä
     * viimeiseen. Mikäli polkua ei ole olemassa, palauttaa null.
     * 
     * @param lahto lähtönode
     * @param maali maalinode
     * @return lähdön ja maalin välisen lyhyimmän polun nodet
     */
    public static Node[] search(Node lahto, Node maali) {

        NodeJono openSet = new NodeJono(maali);
        HashSet<Node> closedSet = new HashSet<Node>();

        openSet.lisaaNode(lahto);

        while (!openSet.onTyhja()) {
            Node kasiteltava = openSet.poimiNode();
            if (kasiteltava == maali){
                return generoiPolku(maali);
            }
            
            closedSet.add(kasiteltava);
            
            kasitteleNaapurit(openSet, closedSet, kasiteltava);
            
        }
        
        return null;
    }

    /**
     * Lisää annetun noden kaikki naapurit annettuun NodeJonoon jos niitä ei jo
     * löydy closedSetistä. Samalla naapurien g-arvo ja edeltävä solmu 
     * päivitetään tarvittaessa.
     *
     * @param openSet NodeJono, johon naapurit lisätään
     * @param closedSet Set, josta löytyviä naapureita ei lisätä
     * @param lisattava Node, jonka naapurit käsitellään
     */
    private static void kasitteleNaapurit(NodeJono openSet, HashSet<Node> closedSet, Node lisattava) {
        for (Node n : lisattava.getNaapurit()) {
            if (!closedSet.contains(n)) {
                if (n.yritaAsettajaaEdeltaja(lisattava)){
                    openSet.lisaaNode(n);
                }
            }
        }
    }
    
    /**
     * Generoi palautettavan polun taulukkoesityksen
     * @param maali maalinode, johon polku vie
     * @return taulukko, jossa on lähdöstä maaliin vievät nodet (päätepisteet mukaanluettuna) ensimmäisestä viimeiseen
     */
    private static Node[] generoiPolku(Node maali){
        int pituus = polunPituus(maali);
        Node[] polku = new Node[pituus];
        int indeksi = pituus - 1;
        
        Node kasiteltava = maali;
        while (kasiteltava != null){
            polku[indeksi] = kasiteltava;
            kasiteltava = kasiteltava.getEdeltaja();
            indeksi--;
        }
        
        return polku;
    }
    
    /**
     * Laskee annettuun maalisolmuun vievän polun pituuden
     * @param maali maalisolmu
     * @return maalisolmuun vievän polun pituus
     */
    private static int polunPituus(Node maali){
        int pituus = 1;
        Node tutkittava = maali;
        while (tutkittava.getEdeltaja() != null){
            pituus++;
            tutkittava = tutkittava.getEdeltaja();
        }
        
        return pituus;
    }

}
