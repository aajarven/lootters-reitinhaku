# Viikkoraportti 1 (22 h)
## Viikon aikaansaannokset
* Oma priorityqueue-toteutus
* A*-haun eka yritys (ei testejä vielä, en tiedä toimiiko)
* Node-luokan edeltäjän asettaminen valikoivaksi (ei vaihdeta huonompaan)
* Heuristiikan vaihtaminen mahattan-etäisyydeksi, ei heuristiikan ole järkeä laskea turhaan neliöjuuria ja potensseja kun ei niitä varsinaisessakaan etäisyydessä tule olemaan.
* Yksikkötestit JonoAlkiolle ja Noden uusille toiminnallisuuksille, ei vielä AStarille tai itse Nodejonolle.
* Yksikkötestit myös NodeJonolle ja AStarille
* Testaus- ja toteutusdokumenttien aloittaminen
* Vertaisarviointi. Maistuu vähän tekstiseinä mutta tulipahan kirjoitettua.

## Mitä opin
* Verestin muistoja A*-hausta
* Omaa set-toteutusta pohdiskellessa googlailin mitä muut ovat tehneet 
* Tajusin olleeni aika töhö toteuttaessani priorityqueuen linkitettynä listana. Onhan se nyt ihan tiralta tuttua asiaa, että keko on nopeampi eikä A*-hakua kuitenkaan kiinnosta kuin ensimmäinen alkio. Kannatti tehdä vertaisarvio että huomaa oman tyhmyröintinsä.

## Mikä oli vaikeaa tai jäi epäselväksi
* Meinasi tulla tenkkapoo kun mietin miksi AStar ei mene testejä läpi. Tajusin kuitenkin, että lähtönoden kustannusarvio oli uudelle nodelle default floatin maksimiarvo, mistä johtuen naapureita ei lisätty OpenSettiin koska uusi edeltäjä oli "huonompi" kuin null ja haku tyssäsi heti alkuun. Nyt toimii.
* Kuumottaa tuon nyt käytössä olevan HashSetin korvaaminen omalla toteutuksella, minkään hashihommien toteuttamisesta kun ole kokemusta muuten kuin tiran hyvin pintapuolinen käsittely. Toisaalta mietin, että voin ehkä fukata systeemiä, koska tiedän, että jokaisella nodella on koordinaatit, joten voin esim joko tallentaa ne sortatussa 1d-taulukossa tai 2d-taulukollisen booleaneja. Ehkä tämä tästä.
* Tajusin myös tenttiviikon hiipivän lähemmäs yllättävän nopeasti. Menevätkö mahdollisuudet viiteen opintopisteeseen jos toteuttaakin vain toisen IDA*- ja IDS-hauista vai pitääkö rutistaa väkisin molemmat?
* Mitä tarkoittaa "Ohjelman toiminnan empiirisen testauksen tulosten esittäminen graafisessa muodossa."
* Nolosti en saanut vertaisarvioitavaa koodia käännettyä, enkä tiedä miksi. Osaltaan vaikutti varmaan debuggauskestävyyden loppuminen kun ensin mietin nolon kauan miksi muka JavaFXn pitäisi tulla kasissa mukana mutta silti ei löydy. Se pieni ero Oraclen ja openjdkn välillä.

# Mitä seuraavaksi
* Ensi viikolla rykäisen varsinaisen reitinhakuhomman valmiiksi ja kirjoittelen dokumentteja.
* Pitää myös miettiä vertaisarvioinnissa esitettyä pointtia closedSetistä.
