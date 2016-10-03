/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fi.antonbury.reitinhaku.haku;

import fi.antonbury.reitinhaku.utils.NodeGeneraattori;
import fi.antonbury.reitinhaku.utils.PolkuGeneraattori;
import fi.antonbury.reitinhaku.utils.Prioriteettijono;
import java.util.HashSet;

/**
 * A*-haku
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

        Prioriteettijono openSet = new Prioriteettijono(maali);
        HashSet<Node> closedSet = new HashSet<Node>();

        openSet.lisaaNode(lahto);

        while (!openSet.onTyhja()) {
            Node kasiteltava = openSet.poimiNode();
            if (kasiteltava == maali){
                return PolkuGeneraattori.generoiPolku(kasiteltava);
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
    private static void kasitteleNaapurit(Prioriteettijono openSet, HashSet<Node> closedSet, Node lisattava) {
        for (Node n : lisattava.getNaapurit()) {
            if (!closedSet.contains(n)) {
                if (n.yritaAsettajaaEdeltaja(lisattava)){
                    openSet.lisaaNode(n);
                }
            }
        }
    }
}