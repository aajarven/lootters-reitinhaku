/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fi.antonbury.reitinhaku.utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Luolan tiedostosta lukemiseen käytettävä luokka
 * @author anni
 */
public class LuolaLukija {

    /**
     * Lukee annetussa polussa olevasta tekstitiedostosta luolan. Luola
     * tallennetaan välilyönneillä ja rivinvaihdoilla erotettuna taulukkona
     * ykkösiä ja nollia, joista ykköset edustavat ruutuja, joihin voi kävellä
     * ja nollat ruutuja, joihin ei voi.
     *
     * Tiedoston ensimmäisen rivin tulee sisältää kaksi välilyönnillä erotettua
     * kokonaislukua, jotka kertovat luolan leveyden ja korkeuden.
     * Risuaitamerkillä alkavia rivejä ei käsitellä.
     *
     * @param tiedostonimi polku luoladatan sisältävään tiedostoon
     * @return boolean-taulukko, jossa true edustaa kuljettavissa olevia ruutuja
     */
    public static boolean[][] lueLuola(String tiedostonimi) throws IOException {

        Scanner lukija = null;
        lukija = new Scanner(new File(tiedostonimi));

        boolean[][] luola = alustaLuolataulukko(lukija);
        taytaLuola(luola, lukija);

        return luola;

    }

    /**
     * Generoi annetun scannerin lukemaa tiedostoa vastaavan boolean-taulukon
     *
     * @param luola kaksiulotteinen boolean-taulukko, jossa true vastaa
     * kuljettavaa nodea ja false kulkukelvotonta
     * @param lukija haluttua tiedostoa
     * @throws IOException
     */
    private static void taytaLuola(boolean[][] luola, Scanner lukija) throws IOException {
        int sarakkeet = luola[0].length;
        int riviNro = 0;

        while (lukija.hasNextLine()) {
            String rivi = lukija.nextLine();

            if (!onDatarivi(rivi)) {
                continue;
            }

            String[] luvut = rivi.split(" ");
            if (luvut.length != sarakkeet) {
                throw new IOException("Rivillä '" + rivi + "' oli odottamaton "
                        + "määrä lukuja (havaittu " + luvut.length
                        + ", odotettiin " + sarakkeet + "). Suoritus lopetetaan.");
            }

            for (int i = 0; i < luvut.length; i++) {
                try {
                    int luku = Integer.parseInt(luvut[i]);
                    if (luku == 0) {
                        luola[riviNro][i] = false;
                    } else {
                        luola[riviNro][i] = true;
                    }
                } catch (NumberFormatException e) {
                    System.err.println("Luolatiedoston rivi '" + rivi
                            + "' sisälsi arvon jota ei voinut parsia "
                            + "kokonaisluvuksi.");
                    throw (e);
                }
            }

            riviNro++;
        }
    }

    /**
     * Luo tiedoston alussa speksatun kokoisen tyhjän taulukon
     *
     * @param lukija scanner halutun tiedoston lukemiseen
     * @return tyhjä kaksiulotteinen boolean-taulukko
     */
    private static boolean[][] alustaLuolataulukko(Scanner lukija) {
        String ekarivi;

        do {
            ekarivi = lukija.nextLine();
        } while (!onDatarivi(ekarivi));

        String[] luvut = ekarivi.split(" ");
        int rivit = 0;
        int sarakkeet = 0;

        try {
            rivit = Integer.parseInt(luvut[0]);
            sarakkeet = Integer.parseInt(luvut[1]);
        } catch (NumberFormatException e) {
            System.err.println("Luolatiedoston ensimmäisen rivin tulee "
                    + "sisältää kaksi välilyönneillä erotettua lukua, joista "
                    + "ensimmäinen kertoo luolataulukon rivien määrän ja "
                    + "toinen sarakkeiden. Esimerkki luolatiedoston sisällöstä:"
                    + "\n"
                    + "3 4\n"
                    + "1 0 0 1\n"
                    + "1 1 0 1\n"
                    + "0 1 1 1\n");
            throw (e);
        }

        return new boolean[rivit][sarakkeet];
    }

    /**
     * Palauttaa false jos rivi on tyhjä tai alkaa risuaitamerkillä (#), muuten
     * true.
     *
     * @param rivi tutkittava rivi
     */
    private static boolean onDatarivi(String rivi) {
        return !(rivi.length() == 0 || rivi.charAt(0) == '#');
    }

}
