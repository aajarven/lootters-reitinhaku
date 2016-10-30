/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fi.antonbury.reitinhaku.testaus;

import fi.antonbury.reitinhaku.haku.AStar;
import fi.antonbury.reitinhaku.haku.IDAStar;
import fi.antonbury.reitinhaku.haku.Leveyshaku;
import fi.antonbury.reitinhaku.haku.Node;
import fi.antonbury.reitinhaku.utils.LuolaLukija;
import fi.antonbury.reitinhaku.utils.NodeGeneraattori;
import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author anni
 */
public class Main {

    public static void main(String[] args) {
        //Suorituskykytestaus.testaaAvoimella();
        //Suorituskykytestaus.testaaSokkelolla();

        Scanner lukija = new Scanner(System.in);

        System.out.println("Anna polku tiedostoon, josta luola luetaan:");
        String tiedostonimi = lukija.nextLine();

        boolean[][] luolaboolean = null;
        try {
            luolaboolean = LuolaLukija.lueLuola(tiedostonimi);
        } catch (IOException ex) {
            System.out.println("Virhe luolaa luettaessa. Onhan tiedostonimi oikein?");
            System.exit(0);
        }


            int jahdattavaX = 0;
            int jahdattavaY = 0;
            int jahtaajaX = 0;
            int jahtaajaY = 0;
            int maaliX = 0;
            int maaliY = 0;
        try {
            System.out.println("Anna jahdattavan x-koordinaatti alussa:");
            jahdattavaX = lukija.nextInt();
            System.out.println("Anna jahdattavan y-koordinaatti alussa:");
            jahdattavaY = lukija.nextInt();
            System.out.println("Anna jahtaajan x-koordinaatti alussa:");
            jahtaajaX = lukija.nextInt();
            System.out.println("Anna jahtaajan y-koordinaatti alussa:");
            jahtaajaY = lukija.nextInt();
            System.out.println("Anna maalin x-koordinaatti alussa:");
            maaliX = lukija.nextInt();
            System.out.println("Anna maalin y-koordinaatti alussa:");
            maaliY = lukija.nextInt();
        } catch (InputMismatchException e){
            System.out.println("Kelpaamaton syöte, koordinaattien tulee olla kokonaislukuja.");
            System.exit(0);
        }

        Node[][] luola = NodeGeneraattori.generoiNodet(luolaboolean);
        luola[jahdattavaY][jahdattavaX].nollaaKustannusNodeenAsti();
        Node[] maali = AStar.hae(luola[jahdattavaY][jahdattavaX], luola[maaliY][maaliX]);

        luola = NodeGeneraattori.generoiNodet(luolaboolean);
        luola[jahtaajaY][jahtaajaX].nollaaKustannusNodeenAsti();

        System.out.println("Valitse käytettävä haku:\n"
                + "1 - leveyshaku\n"
                + "2 - IDA*");
        int haku = lukija.nextInt();

        Node[] polku = null;
        switch (haku) {
            case 1:
                polku = Leveyshaku.hae(luola[jahtaajaY][jahtaajaX], maali);
                break;
            case 2:
                polku = IDAStar.hae(luola[jahtaajaY][jahtaajaX], maali, luola.length, luola[0].length);
                break;
            default:
                System.out.println("Virheellinen hakuvalinta, vain kokonaisluvut 1 ja 2 kelpaavat.");
                System.exit(0);
        }

        if (polku != null) {
            System.out.println("\nJahtaajan polku jahdattavan luo:");
            for (Node n : polku) {
                System.out.println(n.getX() + ", " + n.getY());
            }
        } else {
            System.out.println("Polkua ei löydy");
        }

    }
}
