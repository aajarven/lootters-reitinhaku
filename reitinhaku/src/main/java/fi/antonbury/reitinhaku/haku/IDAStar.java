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
    
    /**
     * Etsii reitin annetusta lähtönodesta maalipolulle annetunkokoisella kartalla
     * @param lahto node, josta lähdetään
     * @param maali lista nodeja, jotka toimivat maalina siten, että yhden askeleen jälkeen maali on listan toinen node jne
     * @param kokoX navigoitavan kartan koko x-suunnassa
     * @param kokoY navigoitavan kartan koko y-suunnassa
     * @return 
     */
    public static Node[] hae(Node lahto, Node[] maali, int kokoX, int kokoY){
        
        int[][] minimikustannukset = luoMinimikustannustaulukko(kokoX, kokoY);
        int ylaraja = 0; // tutkittavien nodejen maksimi (heuristiikka + kustannus) nodeen asti
        
        while (ylaraja < maali.length){
            Node[] palautettu = etsiNaapureista(lahto, 0, ylaraja, maali[ylaraja], minimikustannukset);
            if (palautettu != null){
                return palautettu;
            } else {
                ylaraja++;
            }
        }
        
        return null;
    }
    
    private static Node[] etsiNaapureista(Node n, int kustannus, int ylaraja, Node maali, int[][] minimikustannukset){
        
        if (minimikustannukset[n.getX()][n.getY()] < kustannus){
            return null;
        }
        
        minimikustannukset[n.getX()][n.getY()] = kustannus;
        
        double kokonaiskustannus = kustannus + n.heuristiikka(maali);
        
        if (kokonaiskustannus > ylaraja){
            return null;
        } else if (n.equals(maali)){
            Node[] palautettava = new Node[kustannus+1];
            palautettava[kustannus] = n;
            return palautettava;
        }
        
        for (Node naapuri : n.getNaapurit()){
            Node[] palautettu = etsiNaapureista(naapuri, kustannus+1, ylaraja, maali, minimikustannukset);
            if (palautettu != null){
                palautettu[kustannus] = n;
                return palautettu;
            }
        }
        
        return null;
    }
    
    private static int[][] luoMinimikustannustaulukko(int x, int y){
        int[][] minimikustannukset = new int[x][y];
        
        for (int i=0; i<x; i++){
            for (int j=0; j<y; j++){
                minimikustannukset[i][j] = Integer.MAX_VALUE;
            }
        }
        
        return minimikustannukset;
    }
    
}
