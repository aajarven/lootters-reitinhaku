/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package testaus;

import fi.antonbury.reitinhaku.haku.AStar;
import fi.antonbury.reitinhaku.haku.Leveyshaku;
import fi.antonbury.reitinhaku.haku.Node;
import fi.antonbury.reitinhaku.utils.LuolaLukija;
import fi.antonbury.reitinhaku.utils.NodeGeneraattori;
import java.io.IOException;

/**
 *
 * @author Anni Järvenpää
 */
public class Suorituskykytestaus {
    
    public static void testaaSokkelolla(){
        System.out.println("Sokkelo 20x20");
        System.out.println("boolean\tnodet\tA*\tBFS");
        for(int i=0; i<20; i++){
            testaaLuolalla("resources/testiluolat/20-20.txt", 0, 0, 15, 19, 18, 19);
        }
    }
    
    private static void testaaLuolalla(String tiedosto, int jahdattavaX, int jahdattavaY, int jahtaajaX, int jahtaajaY, int maaliX, int maaliY){
        long t0 = System.currentTimeMillis();
        boolean[][] luolaboolean = null;
        try {
            luolaboolean = LuolaLukija.lueLuola(tiedosto);
        } catch (IOException ex) {
        }
        long t1 = System.currentTimeMillis();
        long booleanGenerointiAika = t1-t0;
        
        t0 = System.currentTimeMillis(); 
        Node[][] luola = NodeGeneraattori.generoiNodet(luolaboolean);
        t1 = System.currentTimeMillis(); 
        long nodeGenerointiAika = t1 - t0;
        
        t0 = System.currentTimeMillis(); 
        luola[jahdattavaY][jahdattavaX].nollaaKustannusNodeenAsti(); //TODO aseta paikat
        Node[] maali = AStar.hae(luola[jahdattavaY][jahdattavaX], luola[maaliY][maaliX]);
        t1 = System.currentTimeMillis();
        long AstarAika = t1 - t0;
        
        luola = NodeGeneraattori.generoiNodet(luolaboolean);
        
        t0 = System.currentTimeMillis(); 
        luola[jahtaajaY][jahtaajaX].nollaaKustannusNodeenAsti();
        Node[] polku = Leveyshaku.hae(luola[jahtaajaY][jahtaajaX], maali);
        t1 = System.currentTimeMillis();
        long leveyshakuAika = t1 - t0;
        
        System.out.println(booleanGenerointiAika + "\t" + nodeGenerointiAika + "\t" + AstarAika + "\t" + leveyshakuAika);
    }
    
}
