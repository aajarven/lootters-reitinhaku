/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fi.antonbury.reitinhaku.testaus;

import fi.antonbury.reitinhaku.haku.AStar;
import fi.antonbury.reitinhaku.haku.IDAStar;
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
        System.out.println("Leveyshaku: avoin 80x80, reitti löytyy");
        System.out.println("pituus\tboolean\tnodet\tA*\tBFS");
        for(int i=0; i<20; i++){
            testaaLeveyshakuLuolalla("resources/testiluolat/80avoin.txt", 0, 0, 79, 79, 0, 79);
        }
        for(int i=0; i<20; i++){
            testaaLeveyshakuLuolalla("resources/testiluolat/80avoin.txt", 0, 0, 69, 69, 0, 69);
        }
        for(int i=0; i<20; i++){
            testaaLeveyshakuLuolalla("resources/testiluolat/80avoin.txt", 0, 0, 59, 59, 0, 59);
        }
        for(int i=0; i<20; i++){
            testaaLeveyshakuLuolalla("resources/testiluolat/80avoin.txt", 0, 0, 49, 49, 0, 49);
        }
        for(int i=0; i<20; i++){
            testaaLeveyshakuLuolalla("resources/testiluolat/80avoin.txt", 0, 0, 39, 39, 0, 39);
        }
        for(int i=0; i<20; i++){
            testaaLeveyshakuLuolalla("resources/testiluolat/80avoin.txt", 0, 0, 29, 29, 0, 29);
        }
        
        System.out.println("IDA*: avoin 80x80, reitti löytyy");
        System.out.println("pituus\tboolean\tnodet\tA*\tIDA*");
        for(int i=0; i<20; i++){
            testaaIDAStarLuolalla("resources/testiluolat/80avoin.txt", 0, 0, 79, 79, 0, 79);
        }
        for(int i=0; i<20; i++){
            testaaIDAStarLuolalla("resources/testiluolat/80avoin.txt", 0, 0, 69, 69, 0, 69);
        }
        for(int i=0; i<20; i++){
            testaaIDAStarLuolalla("resources/testiluolat/80avoin.txt", 0, 0, 59, 59, 0, 59);
        }
        for(int i=0; i<20; i++){
            testaaIDAStarLuolalla("resources/testiluolat/80avoin.txt", 0, 0, 49, 49, 0, 49);
        }
        for(int i=0; i<20; i++){
            testaaIDAStarLuolalla("resources/testiluolat/80avoin.txt", 0, 0, 39, 39, 0, 39);
        }
        for(int i=0; i<20; i++){
            testaaIDAStarLuolalla("resources/testiluolat/80avoin.txt", 0, 0, 29, 29, 0, 29);
        }

        System.out.println("\nLeveyshaku: avoin 80x80, takaa-ajo");
        System.out.println("156");
        System.out.println("pituus\tboolean\tnodet\tA*\tBFS");
        for(int i=0; i<20; i++){
            testaaLeveyshakuLuolalla("resources/testiluolat/80avoin.txt", 1, 1, 0, 0, 79, 79);
        }
        
        System.out.println("136");
        System.out.println("pituus\tboolean\tnodet\tA*\tBFS");
        for(int i=0; i<20; i++){
            testaaLeveyshakuLuolalla("resources/testiluolat/80avoin.txt", 1, 1, 0, 0, 69, 69);
        }
        
        System.out.println("116");
        System.out.println("pituus\tboolean\tnodet\tA*\tBFS");
        for(int i=0; i<20; i++){
            testaaLeveyshakuLuolalla("resources/testiluolat/80avoin.txt", 1, 1, 0, 0, 59, 59);
        }
        
        System.out.println("96");
        System.out.println("pituus\tboolean\tnodet\tA*\tBFS");
        for(int i=0; i<20; i++){
            testaaLeveyshakuLuolalla("resources/testiluolat/80avoin.txt", 1, 1, 0, 0, 49, 49);
        }
        
        System.out.println("76");
        System.out.println("pituus\tboolean\tnodet\tA*\tBFS");
        for(int i=0; i<20; i++){
            testaaLeveyshakuLuolalla("resources/testiluolat/80avoin.txt", 1, 1, 0, 0, 39, 39);
        }
        
        System.out.println("56");
        System.out.println("pituus\tboolean\tnodet\tA*\tBFS");
        for(int i=0; i<20; i++){
            testaaLeveyshakuLuolalla("resources/testiluolat/80avoin.txt", 1, 1, 0, 0, 29, 29);
        }
        
        System.out.println("\nIDA*: avoin 80x80, takaa-ajo");
        System.out.println("156");
        System.out.println("pituus\tboolean\tnodet\tA*\tIDA*");
        for(int i=0; i<20; i++){
            testaaIDAStarLuolalla("resources/testiluolat/80avoin.txt", 1, 1, 0, 0, 79, 79);
        }
        
        System.out.println("136");
        System.out.println("pituus\tboolean\tnodet\tA*\tIDA*");
        for(int i=0; i<20; i++){
            testaaIDAStarLuolalla("resources/testiluolat/80avoin.txt", 1, 1, 0, 0, 69, 69);
        }
        
        System.out.println("116");
        System.out.println("pituus\tboolean\tnodet\tA*\tIDA*");
        for(int i=0; i<20; i++){
            testaaIDAStarLuolalla("resources/testiluolat/80avoin.txt", 1, 1, 0, 0, 59, 59);
        }
        
        System.out.println("96");
        System.out.println("pituus\tboolean\tnodet\tA*\tIDA*");
        for(int i=0; i<20; i++){
            testaaIDAStarLuolalla("resources/testiluolat/80avoin.txt", 1, 1, 0, 0, 49, 49);
        }
        
        System.out.println("76");
        System.out.println("pituus\tboolean\tnodet\tA*\tIDA*");
        for(int i=0; i<20; i++){
            testaaIDAStarLuolalla("resources/testiluolat/80avoin.txt", 1, 1, 0, 0, 39, 39);
        }
        
        System.out.println("56");
        System.out.println("pituus\tboolean\tnodet\tA*\tIDA*");
        for(int i=0; i<20; i++){
            testaaIDAStarLuolalla("resources/testiluolat/80avoin.txt", 1, 1, 0, 0, 29, 29);
        }
    }
    
    public static void testaaSokkelolla(){
        System.out.println("Leveyshaku: sokkelo 20x20, reitti löytyy");
        System.out.println("pituus\tboolean\tnodet\tA*\tBFS");
        for(int i=0; i<20; i++){
            testaaLeveyshakuLuolalla("resources/testiluolat/20-20.txt", 0, 0, 15, 19, 18, 19);
        }
        
        System.out.println("\nLeveyshaku: sokkelo 40x40, reitti löytyy");
        System.out.println("pituus\tboolean\tnodet\tA*\tBFS");
        for(int i=0; i<20; i++){
            testaaLeveyshakuLuolalla("resources/testiluolat/40-40.txt", 0, 0, 39, 39, 0, 39);
        }
        
        System.out.println("\nLeveyshaku: sokkelo 50x50, reitti löytyy");
        System.out.println("pituus\tboolean\tnodet\tA*\tBFS");
        for(int i=0; i<20; i++){
            testaaLeveyshakuLuolalla("resources/testiluolat/50-50.txt", 0, 0, 49, 49, 0, 49);
        }
        
        System.out.println("\nLeveyshaku: sokkelo 60x60, reitti löytyy");
        System.out.println("pituus\tboolean\tnodet\tA*\tBFS");
        for(int i=0; i<20; i++){
            testaaLeveyshakuLuolalla("resources/testiluolat/60-60.txt", 0, 0, 58, 58, 0, 59);
        }
        
        System.out.println("\nLeveyshaku: sokkelo 70x70, reitti löytyy");
        System.out.println("pituus\tboolean\tnodet\tA*\tBFS");
        for(int i=0; i<20; i++){
            testaaLeveyshakuLuolalla("resources/testiluolat/70-70.txt", 0, 0, 64, 64, 64, 64);
        }
        
        System.out.println("\nLeveyshaku: sokkelo 80x80, reitti löytyy");
        System.out.println("pituus\tboolean\tnodet\tA*\tBFS");
        for(int i=0; i<20; i++){
            testaaLeveyshakuLuolalla("resources/testiluolat/80-80.txt", 0, 0, 79, 79, 79, 79);
        }
        
        System.out.println("\nLeveyshaku: sokkelo 20x20, takaa-ajo");
        System.out.println("pituus\tboolean\tnodet\tA*\tBFS");
        for(int i=0; i<20; i++){
            testaaLeveyshakuLuolalla("resources/testiluolat/20-20.txt", 1, 1, 0, 0, 15, 19);
        }
        
        System.out.println("\nLeveyshaku: sokkelo 40x40, takaa-ajo");
        System.out.println("pituus\tboolean\tnodet\tA*\tBFS");
        for(int i=0; i<20; i++){
            testaaLeveyshakuLuolalla("resources/testiluolat/40-40.txt", 1, 1, 0, 0, 39, 39);
        }
        
        System.out.println("\nLeveyshaku: sokkelo 50x50, takaa-ajo");
        System.out.println("pituus\tboolean\tnodet\tA*\tBFS");
        for(int i=0; i<20; i++){
            testaaLeveyshakuLuolalla("resources/testiluolat/50-50.txt", 1, 1, 0, 0, 49, 49);
        }
        
        System.out.println("\nLeveyshaku: sokkelo 60x60, takaa-ajo");
        System.out.println("pituus\tboolean\tnodet\tA*\tBFS");
        for(int i=0; i<20; i++){
            testaaLeveyshakuLuolalla("resources/testiluolat/60-60.txt", 1, 1, 0, 0, 58, 58);
        }
        
        System.out.println("\nLeveyshaku: sokkelo 70x70, takaa-ajo");
        System.out.println("pituus\tboolean\tnodet\tA*\tBFS");
        for(int i=0; i<20; i++){
            testaaLeveyshakuLuolalla("resources/testiluolat/70-70.txt", 1, 1, 0, 0, 69, 69);
        }
        
        System.out.println("\nLeveyshaku: sokkelo 80x80, takaa-ajo");
        System.out.println("pituus\tboolean\tnodet\tA*\tBFS");
        for(int i=0; i<20; i++){
            testaaLeveyshakuLuolalla("resources/testiluolat/80-80.txt", 1, 1, 0, 0, 79, 79);
        }
        
        System.out.println("\n\nIDA*: sokkelo 20x20, reitti löytyy");
        System.out.println("pituus\tboolean\tnodet\tA*\tIDA*");
        for(int i=0; i<20; i++){
            testaaIDAStarLuolalla("resources/testiluolat/20-20.txt", 0, 0, 15, 19, 18, 19);
        }
        
        System.out.println("\nIDA*: sokkelo 40x40, reitti löytyy");
        System.out.println("pituus\tboolean\tnodet\tA*\tIDA*");
        for(int i=0; i<20; i++){
            testaaIDAStarLuolalla("resources/testiluolat/40-40.txt", 0, 0, 39, 39, 0, 39);
        }
        
        System.out.println("\nIDA*: sokkelo 50x50, reitti löytyy");
        System.out.println("pituus\tboolean\tnodet\tA*\tIDA*");
        for(int i=0; i<20; i++){
            testaaIDAStarLuolalla("resources/testiluolat/50-50.txt", 0, 0, 49, 49, 0, 49);
        }
        
        System.out.println("\nIDA*: sokkelo 60x60, reitti löytyy");
        System.out.println("pituus\tboolean\tnodet\tA*\tIDA*");
        for(int i=0; i<20; i++){
            testaaIDAStarLuolalla("resources/testiluolat/60-60.txt", 0, 0, 58, 58, 0, 59);
        }
        
//        System.out.println("\nIDA*: sokkelo 70x70, reitti löytyy");
//        System.out.println("pituus\tboolean\tnodet\tA*\tIDA*");
//        for(int i=0; i<20; i++){
//            testaaIDAStarLuolalla("resources/testiluolat/70-70.txt", 0, 0, 69, 69, 0, 69);
//        }
//        
//        System.out.println("\nIDA*: sokkelo 80x80, reitti löytyy");
//        System.out.println("pituus\tboolean\tnodet\tA*\tIDA*");
//        for(int i=0; i<20; i++){
//            testaaIDAStarLuolalla("resources/testiluolat/80-80.txt", 0, 0, 79, 79, 0, 79);
//        }
        
        System.out.println("\nIDA*: sokkelo 20x20, takaa-ajo");
        System.out.println("pituus\tboolean\tnodet\tA*\tIDA*");
        for(int i=0; i<20; i++){
            testaaIDAStarLuolalla("resources/testiluolat/20-20.txt", 1, 1, 0, 0, 15, 19);
        }
        
        System.out.println("\nIDA*: sokkelo 40x40, takaa-ajo");
        System.out.println("pituus\tboolean\tnodet\tA*\tIDA*");
        for(int i=0; i<20; i++){
            testaaIDAStarLuolalla("resources/testiluolat/40-40.txt", 1, 1, 0, 0, 39, 39);
        }
        
//        System.out.println("\nIDA*: sokkelo 50x50, takaa-ajo");
//        System.out.println("pituus\tboolean\tnodet\tA*\tIDA*");
//        for(int i=0; i<20; i++){
//            testaaLeveyshakuLuolalla("resources/testiluolat/50-50.txt", 1, 1, 0, 0, 49, 49);
//        }
//        
//        System.out.println("\nIDA*: sokkelo 60x60, takaa-ajo");
//        System.out.println("pituus\tboolean\tnodet\tA*\tIDA*");
//        for(int i=0; i<20; i++){
//            testaaLeveyshakuLuolalla("resources/testiluolat/60-60.txt", 1, 1, 0, 0, 59, 59);
//        }
//        
//        System.out.println("\nIDA*: sokkelo 70x70, takaa-ajo");
//        System.out.println("pituus\tboolean\tnodet\tA*\tIDA*");
//        for(int i=0; i<20; i++){
//            testaaLeveyshakuLuolalla("resources/testiluolat/70-70.txt", 1, 1, 0, 0, 69, 69);
//        }
//        
//        System.out.println("\nIDA*: sokkelo 80x80, takaa-ajo");
//        System.out.println("pituus\tboolean\tnodet\tA*\tIDA*");
//        for(int i=0; i<20; i++){
//            testaaIDAStarLuolalla("resources/testiluolat/80-80.txt", 1, 1, 0, 0, 79, 79);
//        }
    }
    
    private static void testaaLeveyshakuLuolalla(String tiedosto, int jahdattavaX, int jahdattavaY, int jahtaajaX, int jahtaajaY, int maaliX, int maaliY){
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
        
        int polunpituus = -1;
        if (polku != null){
            polunpituus = polku.length;
        }
        
        System.out.println(polunpituus + "\t" + booleanGenerointiAika/1000 + "\t" + nodeGenerointiAika/1000 + "\t" + AstarAika/1000 + "\t" + leveyshakuAika/1000);
    }
    
    private static void testaaIDAStarLuolalla(String tiedosto, int jahdattavaX, int jahdattavaY, int jahtaajaX, int jahtaajaY, int maaliX, int maaliY){
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
        Node[] polku = IDAStar.hae(luola[jahtaajaY][jahtaajaX], maali, luola.length, luola[0].length);
        t1 = System.nanoTime();
        long leveyshakuAika = t1 - t0;
        
        int polunpituus = -1;
        if (polku != null){
            polunpituus = polku.length;
        }
        
        System.out.println(polunpituus + "\t" + booleanGenerointiAika/1000 + "\t" + nodeGenerointiAika/1000 + "\t" + AstarAika/1000 + "\t" + leveyshakuAika/1000);
    }
    
}
