/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fi.antonbury.reitinhaku.haku;

import java.util.ArrayList;
import java.lang.Math;

/**
 *
 * @author Anni Järvenpää
 */
public class Node {

    /**
     * Noden x-koordinaatti
     */
    private int x;

    /**
     * Noden y-koordinaatti
     */
    private int y;

    /**
     * Kustannus tähän nodeen pääsemiseksi
     */
    private double g;

    /**
     * Solmun naapurit
     */
    private ArrayList<Node> naapurit; // TODO toteuta itse (huom näitä on korkeintaan neljä, naapurilista voi siis hyvin olla vakiosti neljän mittainen?

    /**
     * Solmu, josta tähän solmuun on tultu
     */
    private Node edeltaja;

    /**
     * Konstruktori. Asettaa solmulle halutut koordinaatit, solmun naapurilistan
     * tyhjäksi, edeltäjän nulliksi ja solmuun pääsemiseksi vaaditun
     * kustannusarvion g suurimman järjestelmän tukeman luvun.
     *
     * @param x solmun x-koordinaatti
     * @param y solmun y-koordinaatti
     */
    public Node(int x, int y) {
        this.x = x;
        this.y = y;
        this.g = Double.MAX_VALUE;
        this.naapurit = new ArrayList<Node>();
        this.edeltaja = null;
    }

    /**
     * Lisää solmulle uuden naapurin
     *
     * @param naapuri uusi naapuri
     */
    public void lisaaNaapuri(Node naapuri) {
        naapurit.add(naapuri);
    }

    /**
     * Asettaa solmulle edeltäjän ja päivittää samalla solmunu pääsemiseksi
     * vaaditun kustannuksen jos uusi edeltäjä on edellistä parempi
     *
     * @param edeltaja kandidaatti uudeksi edeltäjäksi
     * @return true, jos edeltäjä vaihtui, muuten false
     */
    public boolean yritaAsettajaaEdeltaja(Node edeltaja) {
        if (edeltaja.getG() + 1 < this.g) {
            this.edeltaja = edeltaja;
            g = edeltaja.getG() + 1;
            return true;
        } else {
            return false;
        }
    }

    public Node getEdeltaja() {
        return edeltaja;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public double getG() {
        return g;
    }

    /**
     * Palauttaa arvion (f=h+g) annettuun maalisolmuun pääsemisen
     * kustannuksesta. Heuristiikkana käytetään euklidista etäisyyttä tästä
     * solmusta maalisolmuun.
     *
     * @param maali maalisolmu
     * @return kustannus
     */
    public double f(Node maali) {
        return g + h(maali);
    }

    /**
     * Heuristiikkafunktio
     *
     * @param maali
     * @return
     */
    private double h(Node maali) {
        return Math.sqrt(Math.pow(x - maali.getX(), 2.0) + Math.pow(y - maali.getY(), 2.0));
    }

    public ArrayList<Node> getNaapurit() {
        return naapurit;
    }
    
    /**
     * Asettaa noden g-arvon nollaksi, jolloin node on lähtönode.
     */
    public void nollaaG(){
        this.g = 0;
    }
}
