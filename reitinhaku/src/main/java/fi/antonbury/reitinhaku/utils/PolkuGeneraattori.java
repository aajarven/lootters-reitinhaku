/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fi.antonbury.reitinhaku.utils;

import fi.antonbury.reitinhaku.haku.Node;

/**
 *
 * @author anni
 */
public class PolkuGeneraattori {
    
        /**
     * Generoi palautettavan polun taulukkoesityksen
     * @param maali maalinode, johon polku vie
     * @return taulukko, jossa on lähdöstä maaliin vievät nodet (päätepisteet mukaanluettuna) ensimmäisestä viimeiseen
     */
    public static Node[] generoiPolku(Node maali){
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
