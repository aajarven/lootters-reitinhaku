/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fi.antonbury.reitinhaku.utils;

import fi.antonbury.reitinhaku.haku.Node;

/**
 * Node-olioiden generointiin käytettävä luokka
 * @author anni
 */
public class NodeGeneraattori {

    public static Node[][] generoiNodet(boolean[][] luola) {
        Node[][] nodet = new Node[luola.length][luola[0].length];

        for (int i = 0; i < luola.length; i++) {
            for (int j = 0; j < luola[0].length; j++) {
                if (luola[i][j]) {
                    nodet[i][j] = new Node(j, i);
                }
            }
        }

        for (int i = 0; i < luola.length; i++) {
            for (int j = 0; j < luola[0].length; j++) {
                if (luola[i][j]) {
                    if (i > 0) {
                        if (luola[i - 1][j]) {
                            nodet[i][j].lisaaNaapuri(nodet[i - 1][j]);
                        }
                    }
                    if (i < luola.length - 1) {
                        if (luola[i + 1][j]) {
                            nodet[i][j].lisaaNaapuri(nodet[i + 1][j]);
                        }
                    }
                    if (j > 0) {
                        if (luola[i][j - 1]) {
                            nodet[i][j].lisaaNaapuri(nodet[i][j - 1]);
                        }
                    }
                    if (j < luola[0].length - 1) {
                        if (luola[i][j + 1]) {
                            nodet[i][j].lisaaNaapuri(nodet[i][j + 1]);
                        }
                    }
                }
            }
        }

        return nodet;
    }

}
