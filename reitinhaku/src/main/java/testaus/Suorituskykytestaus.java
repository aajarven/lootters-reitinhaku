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
    
    public static void testaaAvoimella(){
        System.out.println("Avoin 80x80, reitti löytyy");
        System.out.println("boolean\tnodet\tA*\tBFS");
        for(int i=0; i<20; i++){
            testaaLuolalla("resources/testiluolat/80avoin.txt", 0, 0, 79, 79, 0, 79);
        }
        
        System.out.println("\nAvoin 80x80, takaa-ajo");
        System.out.println("boolean\tnodet\tA*\tBFS");
        for(int i=0; i<20; i++){
            testaaLuolalla("resources/testiluolat/80avoin.txt", 1, 1, 0, 0, 79, 79);
        }
    }
    
    public static void testaaSokkelolla(){
        System.out.println("Sokkelo 20x20, reitti löytyy");
        System.out.println("boolean\tnodet\tA*\tBFS");
        for(int i=0; i<20; i++){
            testaaLuolalla("resources/testiluolat/20-20.txt", 0, 0, 15, 19, 18, 19);
        }
        
        System.out.println("\nSokkelo 40x40, reitti löytyy");
        System.out.println("boolean\tnodet\tA*\tBFS");
        for(int i=0; i<20; i++){
            testaaLuolalla("resources/testiluolat/40-40.txt", 0, 0, 39, 39, 0, 39);
        }
        
        System.out.println("\nSokkelo 80x80, reitti löytyy");
        System.out.println("boolean\tnodet\tA*\tBFS");
        for(int i=0; i<20; i++){
            testaaLuolalla("resources/testiluolat/80-80.txt", 0, 0, 79, 79, 0, 79);
        }
        
        System.out.println("\nSokkelo 20x20, takaa-ajo");
        System.out.println("boolean\tnodet\tA*\tBFS");
        for(int i=0; i<20; i++){
            testaaLuolalla("resources/testiluolat/20-20.txt", 1, 1, 0, 0, 15, 19);
        }
        
        System.out.println("\nSokkelo 40x40, takaa-ajo");
        System.out.println("boolean\tnodet\tA*\tBFS");
        for(int i=0; i<20; i++){
            testaaLuolalla("resources/testiluolat/40-40.txt", 1, 1, 0, 0, 39, 39);
        }
        
        System.out.println("\nSokkelo 80x80, takaa-ajo");
        System.out.println("boolean\tnodet\tA*\tBFS");
        for(int i=0; i<20; i++){
            testaaLuolalla("resources/testiluolat/80-80.txt", 1, 1, 0, 0, 79, 79);
        }
    }
    
    private static void testaaLuolalla(String tiedosto, int jahdattavaX, int jahdattavaY, int jahtaajaX, int jahtaajaY, int maaliX, int maaliY){
        long t0 = System.nanoTime();
        boolean[][] luolaboolean = null;
        try {
            luolaboolean = LuolaLukija.lueLuola(tiedosto);
        } catch (IOException ex) {
        }
        long t1 = System.nanoTime();
        long booleanGenerointiAika = t1-t0;
        
        t0 = System.nanoTime(); 
        Node[][] luola = NodeGeneraattori.generoiNodet(luolaboolean);
        t1 = System.nanoTime(); 
        long nodeGenerointiAika = t1 - t0;
        
        t0 = System.nanoTime(); 
        luola[jahdattavaY][jahdattavaX].nollaaKustannusNodeenAsti(); //TODO aseta paikat
        Node[] maali = AStar.hae(luola[jahdattavaY][jahdattavaX], luola[maaliY][maaliX]);
        t1 = System.nanoTime();
        long AstarAika = t1 - t0;
        
        luola = NodeGeneraattori.generoiNodet(luolaboolean);
        
        t0 = System.nanoTime(); 
        luola[jahtaajaY][jahtaajaX].nollaaKustannusNodeenAsti();
        Node[] polku = Leveyshaku.hae(luola[jahtaajaY][jahtaajaX], maali);
        t1 = System.nanoTime();
        long leveyshakuAika = t1 - t0;
        
        System.out.println(booleanGenerointiAika/1000 + "\t" + nodeGenerointiAika/1000 + "\t" + AstarAika/1000 + "\t" + leveyshakuAika/1000);
    }
    
}
