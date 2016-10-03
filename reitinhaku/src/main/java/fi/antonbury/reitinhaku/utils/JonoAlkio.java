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
    
    private Node node;
    private JonoAlkio seuraaja;

    public JonoAlkio(Node node) {
        this.node = node;
        this.seuraaja = null;
    }

    public JonoAlkio getSeuraaja() {
        return seuraaja;
    }

    public void setSeuraaja(JonoAlkio seuraaja) {
        this.seuraaja = seuraaja;
    }

    public Node getNode() {
        return node;
    }
    
    public double kustannus(Node maali){
        return node.kokonaiskustannus(maali);
    }
    
    
}
