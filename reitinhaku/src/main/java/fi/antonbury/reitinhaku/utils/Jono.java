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
public class Jono {
    
    private JonoAlkio ensimmainen;
    private JonoAlkio viimeinen;

    /**
     * Luo tyhjän jonon
     */
    public Jono() {
        ensimmainen = null;
        viimeinen = null;
    }
    
    /**
     * Lisää jonoon uuden alkion. 
     */
    public void lisaaAlkio(Node node){
        if (ensimmainen == null){
            ensimmainen = new JonoAlkio(node);
            viimeinen = ensimmainen;
        } else {
            JonoAlkio lisattava = new JonoAlkio(node);
            viimeinen.setSeuraaja(lisattava);
            viimeinen = lisattava;
        }
    }
    
    /**
     * Palauttaa jonon ensimmäisen noden
     * @return ensimmäinen node
     */
    public Node poimiNode(){
        if (ensimmainen == null){
            return null;
        }
        
        Node palautettava = ensimmainen.getNode();
        ensimmainen = ensimmainen.getSeuraaja();
        return palautettava;
    }
    
    /**
     * Kertoo onko jono tyhjä
     * @return true jos jono on tyhjä, muuten false
     */
    public boolean onTyhja(){
        return ensimmainen == null;
    }
    
    
}
