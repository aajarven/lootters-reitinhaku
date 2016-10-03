/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fi.antonbury.reitinhaku.utils;

import fi.antonbury.reitinhaku.haku.Node;

/**
 * Korkeintaan neljä uniikkia naapuria sisältävä listatoteutus
 * @author anni
 */
public class NaapuriLista {
    
    private Node[] naapurit;

    /**
     * Luo uuden tyhjän listan
     */
    public NaapuriLista() {
        naapurit = new Node[0];
    }
    
    public void lisaaNaapuri(Node lisattava){
        // Tarkasta onko jo naapureissa
        for (Node n: naapurit){
            if (n.equals(lisattava)){
                return;
            }
        }
        
        // Kopioi vanhat uuteen arrayhyn
        Node[] uusi = new Node[naapurit.length+1];
        for (int i=0; i<naapurit.length; i++){
            uusi[i] = naapurit[i];
        }
        
        // Lisää uusi ja palauta
        uusi[naapurit.length] = lisattava;
        
        naapurit = uusi;
    }
    
    public Node[] getNaapurit(){
        return naapurit;
    }
    
}
