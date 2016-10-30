# Toteutusdokumentti
## Ohjelman yleisrakenne
Ohjelman voi katsoa koostuvan kolmesta erillisestä osasta: ensin generoidaan annetun luolatiedoston perusteella Nodet, sitten etsitään jahattavalle reitti annettuun maalinodeen ja lopuksi generoidaan jahtaajan reitti matkalla jahdattavan luo käyttäen joko BFS-hakua tai IDA\*-hakua. Määrittelydokumenttia kirjoitettaessa suunniteltua IDS-hakua ei toteutettu, sillä se tutkii aina vähintään yhtä paljon nodeja kuin IDA\* ja jo IDA\* oli todellista pelikarttaa muistuttavilla vähänkään suuremmilla syötteillä melko hidas.

Koska ohjelmaa ei ole tarkoitus käyttää sellaisenaan mihinkään vaan upottaa se myöhemmin osaksi peliprojektia, toteutin main-metodin osana testauspakettia. Se sopii sinne loogisesti, sillä sen tärkein sovelluskohde on suorituskykytestien ajaminen. Lisäksi se on yksinkertaista korvata yhdessä suorituskykytestauksen kanssa muun pelin koodilla varsinaisen peliprojektin alkaessa.

Kun ohjelma suoritetaan, se kysyy käyttäjältä ajetaanko suorituskykytestit vai etsitäänkö reitti tietyssä labyrintissa. Mikäli pelaaja valitsee reitinhaun, ohjelma kysyy lisäksi polun käytettävään luolatiedostoon, hahmojen ja maalin sijainnit haun alussa sekä käytetyn hakutyypin (leveyssuuntainen tai IDA\*). Tämän jälkeen ohjelma tulostaa jahtaajan koordinaatit sen matkalla jahdattavan luo.

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
IDA\*-haun varsinainen hakumetodi on hyvin yksinkertainen, se kutsuu ainoastaan etsiNaapureista-metodia kullakin maalipolun solmulla, kunnes reitti löytyy. Tästä syystä ohjelman aikavaativuuden määrääkin etsiNaapureista-metodi. Se on esitetty alla:
<pre>
private static Node[] etsiNaapureista(Node n, int kustannus, int ylaraja, Node maali, int[][] minimikustannukset){

    if (minimikustannukset[n.getX()][n.getY()] < kustannus){ // vakio
        return null;
    }

    minimikustannukset[n.getX()][n.getY()] = kustannus; // vakio

    double kokonaiskustannus = kustannus + n.heuristiikka(maali); // vakio

    if (kokonaiskustannus > ylaraja){ // vakio
        return null;
    } else if (n.equals(maali)){ // vakioita
        Node[] palautettava = new Node[kustannus+1];
        palautettava[kustannus] = n;
        return palautettava;
    }

    for (Node naapuri : n.getNaapurit()){ suoritetaan naapurien määrän verran kertoja
        Node[] palautettu = etsiNaapureista(naapuri, kustannus+1, ylaraja, maali, minimikustannukset); // rekursio
        if (palautettu != null){ // vakio
            palautettu[kustannus] = n;
            return palautettu;
        }
    }

    return null;
}
</pre>
Rekursio etenee aina annetun ylärajan määräämälle syvyydelle. Täten aikavaativuutta dominoi viimeinen kutsu, jolla tehdään O(b^d) hakua, missä b kuvaa jälleen noden naapurien keskimääräistä määrää ja d suurinta hakusyvyyttä.

### Yhteenveto ja huomioita
Projektin oleellisten algoritmien eli liikkuvaan maaliin navigointiin käytettävän muokatun leveyshaun ja IDA\*-haun havaitaan saavuttavan määrittelydokumentissa tavoitteeksi asetetut aikavaativuudet. Samasta vaativuusluokasta huolimatta IDA\*- ja leveyshaun vaatimat ajat vaihtelevat merkittävästi sen mukaan, millaisella kartalla haku suoritetaan, kuten testausdokumentissa on esitetty. Avoimella kartalla näkyy se, kuinka IDA* ei tutki kaikkia b^d solmua vaan heuristiikka rajoittaa solmujen rajoittamista voimakkaasti. Kapeista käytävistä koostuvalla kartalla puolestaan IDA\* menestyy huomattavasti leveyshakua huonommin, sillä samoja solmuja joudutaan tutkimaan uudelleen ja uudelleen ja uusia solmuja kertyy iteraatiokertojen välissä hitaasti kun taas BFS:n tutkimien solmujen määrä pysyy kohtuullisena, sillä kullakin solmulla on vain vähän naapureita.

## Työn puutteet ja parannusehdotukset
Työssä on joitain varsinaisen tutkittavan ongelman ratkaisun tehokkuuteen vaikuttamattomia puutteita kuten A\*-haun epäoptimaalinen aikavaativuus sekä nodejen luomiseen liittyvän naapurien lisäämisen O(n)-aikavaativuus, missä n on jo olemassaolevien naapurien määrä. Lopullisen sovelluskohteen kannalta erityisesti jälkimmäinen ongelma on kuitenkin merkityksetön, sillä pelin tapauksessa karttanodet luodaan pelin alussa ja luomisprosessi poikkeaa jonkin verran tässä käytetystä esimerkiksi sen osalta, mitä tietoja nodeen liittyy.

Lisäksi mikäli käytettävissä olisi ollut enemmän aikaa, olisin mielelläni tutkinut myös muiden algoritmien soveltuvuutta liikkuvan maalin jahtaamiseen. Esimerkiksi ehdotettuun jump point searchiin olisi ollut kiintoisaa perehtyä.

Mikäli ohjelma olisi tarkoitettu käytettäväksi itsenäisesti, olisi käyttöliittymän paranteleminen kannattavaa. Nyt esimerkiksi käyttäjälle ei anneta mahdollisuutta antaa uutta syötettä, mikäli edellinen ei kelpaa, vaan ohjelma kaatuu. Tämän korjaaminen olisi käyttäjämukavuuden kannalta tärkeää.
