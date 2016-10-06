/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fi.antonbury.reitinhaku.haku;

import fi.antonbury.reitinhaku.utils.Jono;
import fi.antonbury.reitinhaku.utils.PolkuGeneraattori;

/**
 * Leveyssuuntainen haku toteutettuna etsimään reitti liikkuvan maalin luo
 * @author anni
 */
public class Leveyshaku {
    
    /**
     * Etsii annetusta lähtösolmusta reitin liikkuvaan maaliin
     * @param lahto lähtösolmu
     * @param maali taulukko nodeja siten, että indeksissä i on maalin sijainti i askeleen jälkeen
     * @return maalin luo vievän reitin nodet taulukossa tai null mikäli reittiä ei ole
     */
    public static Node[] search(Node lahto, Node[] maali){
        Jono jono = new Jono();
        jono.lisaaAlkio(lahto);
        Node parasReitilta = null; // aikaisimmin jahdattavan reitillä oleva löydetty node
        int parasReitiltaPituus = Integer.MAX_VALUE; // monesko tämä node on reitillä
        
        while(!jono.onTyhja()){
            Node tutkittava = jono.poimiNode();
            if (onMaali(tutkittava, maali)){
                return PolkuGeneraattori.generoiPolku(tutkittava);
            } 
            int tutkittavaMoneskoReitilta = moneskoReitilla(tutkittava, maali);
            if (tutkittavaMoneskoReitilta < parasReitiltaPituus){
                parasReitiltaPituus = tutkittavaMoneskoReitilta;
                parasReitilta = tutkittava;
            }
            
            for (Node naapuri : tutkittava.getNaapurit()){
                if (naapuri.getEdeltaja() == null){
                    naapuri.yritaAsettajaaEdeltaja(tutkittava);
                    jono.lisaaAlkio(naapuri);
                }
            }
            
        }
        
        if (parasReitilta == null){
            return null;
        } else {
            return PolkuGeneraattori.generoiPolku(parasReitilta);
        }
    }
    
    private static boolean onMaali(Node node, Node[] maalit){
        if (node.getKustannusNodeenAsti()>=maalit.length){
            return false;
        }
        return maalit[node.getKustannusNodeenAsti()].equals(node);
    }
    
    /**
     * Tutkii, onko annettu node maalireitin osa, jossa jahdattava ei ole vielä
     * käynyt jahtaajan päästessä siihen. Mikäli on, palauttaa monesko osa
     * jahdattavan reittiä on kyseessä, muussa tapauksessa suurimman mahdollisen
     * kokonaislukuarvon.
     * 
     * @param node Tutkittava node
     * @param maalit Maalinodejen lista
     * @return Noden kustannus, mikäli se on pienempi kuin noden indeksi maalilistassa. Muussa tapauksessa Integer.MAX_VALUE
     */
    private static int moneskoReitilla(Node node, Node[] maalit){
        for (int i=0; i < maalit.length; i++){
            if (maalit[i].equals(node)){
                if (i>node.getKustannusNodeenAsti()){
                    return i;
                }
            }
        }
        return Integer.MAX_VALUE;
    }
    
}
