/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fi.antonbury.reitinhaku.utils;

import fi.antonbury.reitinhaku.haku.Node;

/**
 * PriorityQueuen alkio
 * @author anni
 */
public class PrioriteettijonoAlkio extends JonoAlkio{
    
    PrioriteettijonoAlkio seuraaja;

    public PrioriteettijonoAlkio(Node node) {
        super(node);
    }
    
    /**
     * Palauttaa alkion seuraajan
     * @return prioritettijonon seuraava alkio
     */
    @Override
    public PrioriteettijonoAlkio getSeuraaja() {
        return seuraaja;
    }
    
    /**
     * Asettaa alkiolle uuden seuraajan
     * @param seuraaja 
     */
    public void setSeuraaja(PrioriteettijonoAlkio seuraaja){
        this.seuraaja = seuraaja;
    }
    
    /**
     * Palauttaa arvion kokonaiskustannuksesta maaliin pääsemiseksi tämän noden
     * kautta kuljettaessa
     * @param maali maalinode
     * @return kustannusarvio
     */
    public double kustannus(Node maali){
        return node.kokonaiskustannus(maali);
    }
    
    
}
