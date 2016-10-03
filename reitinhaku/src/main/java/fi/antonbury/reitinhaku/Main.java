/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fi.antonbury.reitinhaku;

import fi.antonbury.reitinhaku.haku.AStar;
import fi.antonbury.reitinhaku.haku.Leveyshaku;
import fi.antonbury.reitinhaku.haku.Node;
import fi.antonbury.reitinhaku.utils.LuolaLukija;
import fi.antonbury.reitinhaku.utils.NodeGeneraattori;
import java.io.IOException;

/**
 *
 * @author anni
 */
public class Main {

    public static void main(String[] args) {
        boolean[][] luolaboolean = null;
        try {
            luolaboolean = LuolaLukija.lueLuola("resources/testiluolat/hakuluola1.txt");
        } catch (IOException ex) {
            System.out.println("Virhe luolaa luettaessa");
            
        }
        Node[][] luola = NodeGeneraattori.generoiNodet(luolaboolean);
        luola[0][0].nollaaKustannusNodeenAsti();
        Node[] maali = AStar.search(luola[0][0], luola[9][9]);
        
        luola = NodeGeneraattori.generoiNodet(luolaboolean);
        luola[6][2].nollaaKustannusNodeenAsti();
        Node[] polku = Leveyshaku.search(luola[6][2], maali);
        
        System.out.println(polku);
    }
}
