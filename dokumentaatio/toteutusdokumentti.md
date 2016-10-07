# Toteutusdokumentti
## Ohjelman yleisrakenne
Ohjelman voi katsoa koostuvan kolmesta erillisestä osasta: ensin generoidaan annetun luolatiedoston perusteella Nodet, sitten etsitään jahattavalle reitti annettuun maalinodeen ja lopuksi generoidaan jahtaajam reitti matkalla jahdattavan luo käyttäen joko DFS-hakua tai ________.

### Luolan generointi
Luolan lukemisesta tiedostosta huolehtii LuolaLukija-luokka. Sen lueLuola-metodi saa parametrina tiedostonimen, josta luetaan ensin luolan loopullinen koko ja tyhjän boolean-taulukon luomisen jälkeen tämä taulukko täytetään arvoilla sen mukaan, mitkä ruuduista ovat hahmojen käveltävissä.

Tämän jälkeen saatu boolean-taulukko annetaan NodeGeneraattori-luokan generoiNodet-metodille, joka luo Node-oliot ja asettaa niille naapurit siten, että luotu nodetaulukko vastaa saatua boolean-taulukkoa. Nodet tallennetaan kaksiulotteiseen taulukkoon niiden x- ja y-koordinaattien osoittamassa järjestyksessä.

#### Node
Kukin node-olio tuntee omat koordinaattinsa sekä kaikki naapurinodensa ja edeltäjänsä (solmun, josta nykyisellä verkon läpikäynnillä on saavuttu kyseiseen nodeen). Lisäksi node muistaa kustannuksen nodeen pääsemiseksi. 

Noden naapureista pitää kirjaa NaapuriLista, joka käyttää yksiulotteista taulukkoa naapurinodejen tallentamiseen. Kun uusi node lisätään, tarkastetaan ensin, löytyykä se jo listalta, ja mikäli ei löydy, luodaan uusi suurempi taulukko, lisätään vanhat naapurit sekä uusi naapuri tähän taulukkoon ja hylätään vanha, epätäydellinen taulukko. Toteutus on ArrayListiin verrattuna tehoton, sillä lisäysoperaatio on aina aikavaativuusluokkaa O(n). Tämä ei kuitenkaan ole merkittävä puute, sillä toisin kuin suurimmassa osassa muita sovelluskohteita, on tässä sovelluksessa listalla aina korkeintaan neljä solmua. Näin ollen otaksun suorituskykyvaikutuksen mitättömäksi, erityisesti kun naapurit asetetaan kerran haun alussa, minkä jälkeen ne pysyvät muuttumattomina.

### A*-haku ja prioriteettijono
Toteuttamani A*-haku toimii hyvin pitkälti samoin kuin kurssilla tietorakenteet ja algoritmit esitetty, sillä sitä käytetään vain liikkumattomaan maaliin hakeutumiseen. Haun käyttämä PriorityQueue on kuitenkin toteutettu itse käyttäen linkitettyä listaa ja järjestämisperusteena nodeen pääsemiseksi vaaditun kustannuksen ja heuristiikan summaa. Prioriteettijonon toteutuksessa huolehditaan alkioiden oikean järjestyksen lisäksi siitä, ettei listassa ole duplikaatteja samasta nodesta.

### DFS-haku

## Saavutetut aika- ja tilavaativuudet

## Suorituskyky- ja O-analyysivertailut

## Työn puutteet ja parannusehdotukset

## Lähteet
