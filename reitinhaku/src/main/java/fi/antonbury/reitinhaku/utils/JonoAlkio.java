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
public class JonoAlkio {
    
    Node node;
    private JonoAlkio seuraaja;

    public JonoAlkio(Node node) {
        this.node = node;
        this.seuraaja = null;
    }

    /**
     * Palauttaa alkion tämänhetkisen seuraajan
     * @return seuraaja
     */
    public JonoAlkio getSeuraaja() {
        return seuraaja;
    }

    /**
     * Asettaa alkiolle uuden seuraajan
     * @param seuraaja uusi seuraaja
     */
    public void setSeuraaja(JonoAlkio seuraaja) {
        this.seuraaja = seuraaja;
    }

    /**
     * Palauttaa alkion edustaman noden
     * @return alkion edustama node
     */
    public Node getNode() {
        return node;
    }
}
