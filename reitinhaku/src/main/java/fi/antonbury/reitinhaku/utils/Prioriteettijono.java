/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fi.antonbury.reitinhaku.utils;

import fi.antonbury.reitinhaku.haku.Node;

/**
 * PriorityQueueta muistuttava tietotyyppi, joka säilyttää Nodeja. Tukee uuden
 * noden lisäämistä ja ensimmäisen noden hakemista jonosta. Kun ensimmäinen
 * alkio haetaan, se poistuu jonosta. Nodejen järjestys määräytyy noden
 * f-metodin arvojen perusteella.
 * 
 * @author anni
 */
public class Prioriteettijono {
    
    private Node maali;
    private PrioriteettijonoAlkio ensimmainen;

    /**
     * Luo uuden tyhjän jonon, jonka järjestystä määritettäessä käytetään
     * annettua maalisolmua.
     * @param maali 
     */
    public Prioriteettijono(Node maali) {
        this.maali = maali;
        this.ensimmainen = null;
    }
    
    /**
     * Lisää annetun noden jonoon. Jos sama node löytyy jo jonosta, sitä ei
     * lisätä uudelleen.
     * 
     * @param lisattava Node, joka jonoon lisätään
     */
    public void lisaaNode(Node lisattava){
        double kustannus = lisattava.kokonaiskustannus(maali);
        PrioriteettijonoAlkio uusi = new PrioriteettijonoAlkio(lisattava);
        
        if (ensimmainen == null){
            ensimmainen = new PrioriteettijonoAlkio(lisattava);
            
        } else if (ensimmainen.kustannus(maali) >= kustannus){
            uusi.setSeuraaja(ensimmainen);
            ensimmainen = uusi;
            
            poista(ensimmainen, lisattava);
            
        } else {
            PrioriteettijonoAlkio edellinen = ensimmainen;
            
            while(edellinen.getSeuraaja() != null){
                
                PrioriteettijonoAlkio seuraava = edellinen.getSeuraaja();
                
                // paikka löytyi
                if (seuraava.kustannus(maali) >= kustannus){
                    
                    edellinen.setSeuraaja(uusi);
                    uusi.setSeuraaja(seuraava);
                    
                    // jos lisättävää nodea edustava solmu on jo listan loppuosassa, poistetaan se
                    poista(uusi, lisattava);
                    
                    return;
                }
                
                edellinen = seuraava;
            }
            
            edellinen.setSeuraaja(uusi);
        }
    }
    
    /**
     * Palauttaa jonon ensimmäisen noden ja poistaa sen jonosta
     * @return 
     */
    public Node poimiNode(){
        Node palautettava = ensimmainen.getNode();
        ensimmainen = ensimmainen.getSeuraaja();
        return palautettava;
    }
    
    /**
     * Tutkii, onko annettua alkiota seuraavissa sellaista, jonka node olisi
     * sama kuin poistettava, ja poistaa noden mikäli sellainen löytyy. Annettua
     * alkualkiota ei tutkita tai poisteta, ainoastaan sen jälkeen tulevat.
     * 
     * @param alku alkio, josta tutkiminen aloitetaan
     * @param poistettava node, jota vastaava alkio poistetaan
     */
    private void poista(PrioriteettijonoAlkio alku, Node poistettava){
        PrioriteettijonoAlkio edellinen = alku;
        while (edellinen.getSeuraaja() != null){
            PrioriteettijonoAlkio seuraava = edellinen.getSeuraaja();
            
            if (seuraava.getNode() == poistettava){
                edellinen.setSeuraaja(seuraava.getSeuraaja());
                return;
            }
            
            edellinen = seuraava;
        }
    }
    
    /**
     * Kertoo, onko jono tyhjä
     * @return true, jos jono on tyhjä, muuten false
     */
    public boolean onTyhja(){
        return ensimmainen == null;
    }
}
