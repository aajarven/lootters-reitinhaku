/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fi.antonbury.reitinhaku.haku;

import fi.antonbury.reitinhaku.utils.NaapuriLista;

/**
 * Kartan käveltävää ruutua edustava luokka
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
    private int kustannusNodeenAsti;

    /**
     * Solmun naapurit
     */
    private NaapuriLista naapurit;

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
        this.kustannusNodeenAsti = Integer.MAX_VALUE;
        this.naapurit = new NaapuriLista();
        this.edeltaja = null;
    }

    /**
     * Lisää solmulle uuden naapurin
     *
     * @param naapuri uusi naapuri
     */
    public void lisaaNaapuri(Node naapuri) {
        naapurit.lisaaNaapuri(naapuri);
    }

    /**
     * Asettaa solmulle edeltäjän ja päivittää samalla solmunu pääsemiseksi
     * vaaditun kustannuksen jos uusi edeltäjä on edellistä parempi
     *
     * @param edeltaja kandidaatti uudeksi edeltäjäksi
     * @return true, jos edeltäjä vaihtui, muuten false
     */
    public boolean yritaAsettajaaEdeltaja(Node edeltaja) {
        if (edeltaja.getKustannusNodeenAsti() + 1 < this.kustannusNodeenAsti) {
            this.edeltaja = edeltaja;
            kustannusNodeenAsti = edeltaja.getKustannusNodeenAsti() + 1;
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

    public int getKustannusNodeenAsti() {
        return kustannusNodeenAsti;
    }

    /**
     * Palauttaa arvion (f=h+g) annettuun maalisolmuun pääsemisen
     * kustannuksesta. Heuristiikkana käytetään euklidista etäisyyttä tästä
     * solmusta maalisolmuun.
     *
     * @param maali maalisolmu
     * @return kustannus
     */
    public double kokonaiskustannus(Node maali) {
        return kustannusNodeenAsti + heuristiikka(maali);
    }

    /**
     * Heuristiikkafunktio (manhattan-etäisyys)
     *
     * @param maali
     * @return
     */
    public double heuristiikka(Node maali) {
        return Math.abs(x - maali.getX()) + Math.abs(y - maali.getY());
    }

    public Node[] getNaapurit() {
        return naapurit.getNaapurit();
    }

    /**
     * Asettaa noden g-arvon nollaksi, jolloin node on lähtönode.
     */
    public void nollaaKustannusNodeenAsti() {
        this.kustannusNodeenAsti = 0;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Node) {
            Node vertailtava = (Node) obj;
            return (vertailtava.getX() == this.x && vertailtava.getY() == this.y);
        } else {
            return false;
        }
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 83 * hash + this.x;
        hash = 83 * hash + this.y;
        return hash;
    }
    
    /**
     * Poistaa nodella mahdollisesti olevan edeltäjän ja asettaa nodeen asti 
     * pääsemiseksi vaadittavan kustannuksen maksimiarvoon.
     */
    public void nollaaEdeltaja(){
        this.edeltaja = null;
        this.kustannusNodeenAsti = Integer.MAX_VALUE;
    }

}
