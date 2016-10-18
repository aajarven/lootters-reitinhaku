/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package testaus;

import fi.antonbury.reitinhaku.haku.AStar;
import fi.antonbury.reitinhaku.haku.IDAStar;
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
//        Node[][] luola;
//        boolean[][] luolaboolean = null;
//        try {
//            luolaboolean = LuolaLukija.lueLuola("resources/testiluolat/80-80.txt");
//        } catch (IOException ex) {
//        }
//        
//        luola = NodeGeneraattori.generoiNodet(luolaboolean);
//        
//        Node[] maali = AStar.hae(luola[0][0], luola[79][79]);
//        
//        luola = NodeGeneraattori.generoiNodet(luolaboolean);
//        Node[] polku = IDAStar.hae(luola[0][79], maali, luola.length, luola[0].length);
//        for (Node n: polku){
//            System.out.println(n.getX()+"\t"+n.getY());
//        }
        
        Suorituskykytestaus.testaaSokkelolla();
//        Suorituskykytestaus.testaaAvoimella();
    }
}
