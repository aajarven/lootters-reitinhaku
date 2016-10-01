# Viikkoraportti 1 (15 h)
## Viikon aikaansaannokset
* Oma priorityqueue-toteutus
* A*-haun eka yritys (ei testejä vielä, en tiedä toimiiko)
* Node-luokan edeltäjän asettaminen valikoivaksi (ei vaihdeta huonompaan)
* Heuristiikan vaihtaminen mahattan-etäisyydeksi, ei heuristiikan ole järkeä laskea turhaan neliöjuuria ja potensseja kun ei niitä varsinaisessakaan etäisyydessä tule olemaan.
* Yksikkötestit JonoAlkiolle ja Noden uusille toiminnallisuuksille, ei vielä AStarille tai itse Nodejonolle.
* Yksikkötestit myös NodeJonolle ja AStarille
* Testaus- ja toteutusdokumenttien aloittaminen

## Mitä opin
* Verestin muistoja A*-hausta
* Omaa set-toteutusta pohdiskellessa googlailin mitä muut ovat tehneet 

## Mikä oli vaikeaa tai jäi epäselväksi
* Meinasi tulla tenkkapoo kun mietin miksi AStar ei mene testejä läpi. Tajusin kuitenkin, että lähtönoden kustannusarvio oli uudelle nodelle default floatin maksimiarvo, mistä johtuen naapureita ei lisätty OpenSettiin koska uusi edeltäjä oli "huonompi" kuin null ja haku tyssäsi heti alkuun. Nyt toimii.
* Kuumottaa tuon nyt käytössä olevan HashSetin korvaaminen omalla toteutuksella, minkään hashihommien toteuttamisesta kun ole kokemusta muuten kuin tiran hyvin pintapuolinen käsittely. Toisaalta mietin, että voin ehkä fukata systeemiä, koska tiedän, että jokaisella nodella on koordinaatit, joten voin esim joko tallentaa ne sortatussa 1d-taulukossa tai 2d-taulukollisen booleaneja. Ehkä tämä tästä.
* Tajusin myös tenttiviikon hiipivän lähemmäs yllättävän nopeasti. Menevätkö mahdollisuudet viiteen opintopisteeseen jos toteuttaakin vain toisen IDA*- ja IDS-hauista vai pitääkö rutistaa väkisin molemmat?
* Mitä tarkoittaa "Ohjelman toiminnan empiirisen testauksen tulosten esittäminen graafisessa muodossa."

# Mitä seuraavaksi
*
