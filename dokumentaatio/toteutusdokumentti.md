# Toteutusdokumentti
## Ohjelman yleisrakenne
Ohjelman voi katsoa koostuvan kolmesta erillisestä osasta: ensin generoidaan annetun luolatiedoston perusteella Nodet, sitten etsitään jahattavalle reitti annettuun maalinodeen ja lopuksi generoidaan jahtaajam reitti matkalla jahdattavan luo käyttäen joko BFS-hakua tai IDA\*-hakua. Määrittelydokumenttia kirjoitettaessa suunniteltua IDS-hakua ei toteutettu, sillä se tutkii aina vähintään yhtä paljon nodeja kuin IDA\* ja jo IDA\* oli todellista pelikarttaa muistuttavilla vähänkään suuremmilla syötteillä melko hidas.

### Luolan generointi
Luolan lukemisesta tiedostosta huolehtii LuolaLukija-luokka. Sen lueLuola-metodi saa parametrina tiedostonimen, josta luetaan ensin luolan loopullinen koko ja tyhjän boolean-taulukon luomisen jälkeen tämä taulukko täytetään arvoilla sen mukaan, mitkä ruuduista ovat hahmojen käveltävissä.

Tämän jälkeen saatu boolean-taulukko annetaan NodeGeneraattori-luokan generoiNodet-metodille, joka luo Node-oliot ja asettaa niille naapurit siten, että luotu nodetaulukko vastaa saatua boolean-taulukkoa. Nodet tallennetaan kaksiulotteiseen taulukkoon niiden x- ja y-koordinaattien osoittamassa järjestyksessä.

#### Node
Kukin Node-olio tuntee omat koordinaattinsa sekä kaikki naapurinodensa ja edeltäjänsä (solmun, josta nykyisellä verkon läpikäynnillä on saavuttu kyseiseen nodeen). Lisäksi Node muistaa siihen alkusolmusta pääsemiseksi vaadittavan kustannuksen. 

Noden naapureista pitää kirjaa NaapuriLista, joka käyttää yksiulotteista taulukkoa naapurinodejen tallentamiseen. Kun uusi Node lisätään, tarkastetaan ensin, löytyykä se jo listalta, ja mikäli ei löydy, luodaan uusi suurempi taulukko, lisätään vanhat naapurit sekä uusi naapuri tähän taulukkoon ja hylätään vanha, epätäydellinen taulukko. Toteutus on ArrayListiin verrattuna tehoton, sillä lisäysoperaatio on aina aikavaativuusluokkaa O(n). Tämä ei kuitenkaan ole merkittävä puute, sillä toisin kuin suurimmassa osassa muita sovelluskohteita, on tässä sovelluksessa listalla aina korkeintaan neljä solmua. Näin ollen otaksun suorituskykyvaikutuksen mitättömäksi, erityisesti kun naapurit asetetaan kerran haun alussa, minkä jälkeen ne pysyvät muuttumattomina.

### A\*-haku ja prioriteettijono
Toteuttamani A\*-haku toimii hyvin pitkälti samoin kuin kurssilla tietorakenteet ja algoritmit esitetty, sillä sitä käytetään vain liikkumattomaan maaliin hakeutumiseen. Haun käyttämä PriorityQueue on kuitenkin toteutettu itse käyttäen linkitettyä listaa ja järjestämisperusteena nodeen pääsemiseksi vaaditun kustannuksen ja heuristiikan summaa. Prioriteettijonon toteutuksessa huolehditaan alkioiden oikean järjestyksen lisäksi siitä, ettei listassa ole duplikaatteja samasta nodesta.

### BFS-haku
Toteuttamani Leveyshaku-luokan hae-metodi vastaa perustoiminnallisuudeltaan BFS-hakua. Siihen on kuitenkin lisätty toiminnallisuus, joka tarkastaa, onko node osa jahdattavan myöhempää reittiä vaikka jahdattava ei juuri sillä vuorolla olisikaan kyseisessä Nodessa. Näistä saavutetuista myöhemmin polulla sijaitsevista nodeista pidetään muistissa se, jonka indeksi maalipolulla on pienin. Mikäli algoritmi ei löydä reittiä suoraan samaan ruutuun jahdattavan kanssa, palauttaa algoritmi reitin parhaaseen löydettyyn polulla sijaitsevaan nodeen. Näin vältytään tilanteelta, jossa reitin löytymistä ei tunnisteta vaikka jahtaajan on mahdollista päästä jahdattavan reitille ruutuun, johon jahdattava siirtyisi seuraavalla vuorolla, jolloin jahdattava olisi saatavissa kiinni jäämällä tähän ruutuun odottamaan.

## Saavutetut aika- ja tilavaativuudet
Johtuen ongelman luonteesta osana peliä, jossa jokainen pelialueen ruutu siihen liittyvine tietoineen pidetään muistissa joka tapauksessa, toteutin myös haun siten, että kaikki nodet ovat muistissa koko suorituksen ajan. Näin ollen ohjelman tilavaativuus on luokkaa O(n) missä n on nodejen määrä kartalla. Yleisestä tapauksesta poiketen nodejen naapuruussuhteiden määrä ei vaikuta tähän, sillä tässä konfiguraatiossa kullakin nodella voi olla korkeintaan neljä naapuria, eikä tällainen vakiokerroin vaikuta tilavaativuuteen.

Luettaessa luolaa tiedostosta ja generoitaessa nodeja luodun totuusarvotaulukon perusteella käydään nodetaulukko kerran läpi suorittaen vakioaikaisia operaatioita, eli kunkin niistä aikavaativuus on luokkaa O(n). 


## Suorituskyky- ja O-analyysivertailut
<!--Tutkin ohjelman suorituskykyä kahdentyyppisillä kartoilla: avoimella kartalla, jossa ei ole esteitä, sekä sokkeloisella luolastolla, joka koostuu lähinnä kapeahkoista käytävistä. Kummassakin tapauksessa testasin eri mittaisia reittejä sekä tilanteessa, jossa reitti jahdattavan luo on olemassa että tilanteessa, jossa sellaista ei ole. Kussakin testissä ohjelma tulostaa erikseen neljään suoritusvaiheeseen kuluneet ajat nanosekunneissa: tiedoston lukeminen ja boolean-taulukon luominen, nodejen generointi, jahdattavan reitin etsiminen A\*-haulla ja viimeisenä jahtaajan reitin etsintään kuluva aika.-->
Toteutettujen hakualgoritmien suorituskykyä voidaan tarkastella ohjelmakoodiin perustuvan O-analyysin avulla.

### A\*-haku
Käyttämäni koodi A\*-haulle on esitetty alla. Siihen on lisätty myös eri vaiheiden aikavaativuuksia selventäviä kommentteja. 
<pre>
public static Node[] hae(Node lahto, Node maali) {
    lahto.nollaaKustannusNodeenAsti();        
    Prioriteettijono openSet = new Prioriteettijono(maali);
    openSet.lisaaNode(lahto);

    // kaikki ennen tätä on vakioaikaista
    while (!openSet.onTyhja()) {
        Node kasiteltava = openSet.poimiNode(); // vakioaika
        if (kasiteltava == maali) {
            return PolkuGeneraattori.generoiPolku(kasiteltava); //O(polun pituus)
        }
        kasitteleNaapurit(openSet, kasiteltava);
    }

    return null;
}
</pre>
Noden naapureita käsiteltäessä lisätään jokainen naapuri, joka ei jo löydy closedSetistä, openSettiin. Omassa toteutuksessani tämä on toteutettu prioriteettijonona, johon lisääminen on aikavaativuusluokaltaan O(n) missä n on jonossa olevien alkioiden määrä. Täten koska while-silmukka suoritetaan korkeintaan niin monta kertaa, kuin etsittävässä polussa on nodeja, jolloin nodella ollessa keskimäärin b naapuria (tässä sovelluksessa b<=4 kaikilla kartoilla), nähdään A\*-haun olevan aikavaativuudeltaan O((b^d)^2) = O(b^(2d)).

### BFS
Leveyshaun koodi on nähtävissä alla, kommentit avaavat aikavaativuuksia:
<pre>
public static Node[] hae(Node lahto, Node[] maali) {
    lahto.nollaaKustannusNodeenAsti();
    
    Jono jono = new Jono();
    jono.lisaaAlkio(lahto);
    Node parasReitilta = null; // aikaisimmin jahdattavan reitillä oleva löydetty node
    int parasReitiltaPituus = Integer.MAX_VALUE; // monesko tämä node on reitillä

    // kaikki ennen tätä vakioaikaista
    while (!jono.onTyhja()) {
        Node tutkittava = jono.poimiNode(); // vakioaika
        if (onMaali(tutkittava, maali)) {
            return PolkuGeneraattori.generoiPolku(tutkittava); // O(polun pituus)
        }
        int tutkittavaMoneskoReitilta = moneskoReitilla(tutkittava, maali); // O(polun pituus)
        if (tutkittavaMoneskoReitilta < parasReitiltaPituus) { // koko if vakioaikainen
            parasReitiltaPituus = tutkittavaMoneskoReitilta;
            parasReitilta = tutkittava;
        }

        for (Node naapuri : tutkittava.getNaapurit()) { // koko for O(polun pituus)
            if (naapuri.getEdeltaja() =\ null) {
               naapuri.yritaAsettajaaEdeltaja(tutkittava);
                jono.lisaaAlkio(naapuri);
            }
        }

    }

    if (parasReitilta = null) {
        return null;
    } else {
        return PolkuGeneraattori.generoiPolku(parasReitilta);
    }
}
</pre>
Tästä nähdään helposti, että toteuttamani algoritmi vastaa leveyshaun teoreettista aikavaativuutta O(b^d) missä b on noden keskimääräinen naapurimäärä ja d on etsittävän polun pituus.

###IDA\*


## Työn puutteet ja parannusehdotukset

## Lähteet
