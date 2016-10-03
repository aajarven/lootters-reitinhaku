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
        
        while(!jono.onTyhja()){
            Node tutkittava = jono.poimiNode();
            if (onMaali(tutkittava, maali)){
                return PolkuGeneraattori.generoiPolku(tutkittava);
            }
            
            for (Node naapuri : tutkittava.getNaapurit()){
                if (naapuri.getEdeltaja() == null){
                    naapuri.yritaAsettajaaEdeltaja(tutkittava);
                    jono.lisaaAlkio(naapuri);
                }
            }
            
        }
        
        return null;
    }
    
    private static boolean onMaali(Node node, Node[] maalit){
        if (node.getKustannusNodeenAsti()>=maalit.length){
            return false;
        }
        return maalit[node.getKustannusNodeenAsti()].equals(node);
    }
    
}
