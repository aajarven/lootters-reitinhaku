/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fi.antonbury.reitinhaku.haku;

/**
 *
 * @author anni
 */
public class IDAStar {

    public static Node[] hae(Node lahto, Node[] maali) {
        lahto.nollaaKustannusNodeenAsti();
        
        int ylaraja = 0; // tutkittavien nodejen maksimi (heuristiikka + kustannus) nodeen asti
        while (ylaraja < maali.length) {
            Node[] palautettu = etsiNaapureista(lahto, 0, ylaraja, maali[ylaraja]);
            if (palautettu != null) {
                return palautettu;
            } else {
                ylaraja++;
            }
        }

        return null;
    }

    private static Node[] etsiNaapureista(Node n, int kustannus, int ylaraja, Node maali) {
        double kokonaiskustannus = kustannus + n.heuristiikka(maali);
        if (kokonaiskustannus > ylaraja) {
            return null;
        } else if (n.equals(maali)) {
            Node[] palautettava = new Node[kustannus + 1];
            palautettava[kustannus] = n;
            return palautettava;
        }

        for (Node naapuri : n.getNaapurit()) {
            if (naapuri.yritaAsettajaaEdeltaja(n)) { //TODO mieti onko tämä nyt tarpeellinen ja riittävä
                Node[] palautettu = etsiNaapureista(naapuri, kustannus + 1, ylaraja, maali);
                if (palautettu != null) {
                    palautettu[kustannus] = n;
                    return palautettu;
                }
            }
        }

        return null;
    }
}
