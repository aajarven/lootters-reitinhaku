# Loppupelin reitinhaku Lootters-pelin vihollisille
## Ongelman määrittely
### Pelin idea
Toistaiseksi idean tasolla olevassa Lootters-pelissä hahmot tutkivat manhattan-geometriaa noudattavaa ruutuihin jaettua pelialuetta, jossa vain osa ruuduista on kulkukelpoisia. Kaikki kulkukelpoiset ruudut ovat kulkukustannuksen kannalta identtisiä ja samassa ruudussa voi olla mielivaltaisen monta pelaajaa.

 Hahmojen tavoitteena on löytää tietty esine, minkä jälkeen tulee päästä tiettyyn maali-ruutuun (vertaa esim Afrikan tähti tai PlayStationille julkaistu [The Hunter](https://en.wikipedia.org/wiki/Battle_Hunter)). Esine on mahdollista saada varastettua toiselta pelaajalta laskemalla tämän HP nollaan taistelussa. Tämä edellyttää kuitenkin ensin pelaajan saamista kiinni eli samaan ruutuun pääsemistä. Pelin voittaa se pelaaja, joka saa kuljetettua esineen maaliin.

### Tarkasteltava tilanne
Tässä työssä keskitytään tilanteeseen, jossa kilpaileva pelaaja on jo löytänyt tavoite-esineen ja suuntaa kohti maalia. Oletetaan yksinkertaisuuden vuoksi, että tässä tilanteessa tavoite-esineen löytänyt pelaaja suuntaa lyhyintä reittiä kohti maalia, jolloin tämän kulkema reitti on helppo määrittää käyttäen mitä tahansa reitinhakualgoritmia. Lisäksi oletetaan, etteivät muut pelaajat tee minkäänlaista yhteistyötä tai koordinoi toimintaansa, vaan keskitytään ainoastaan kahden pelaajan dynamiikkaan.

Kutsutaan näitä hahmoja jahdattavaksi (tavoite-esineen löytänyt hahmo) ja jahtaajaksi (esinettä itselleen taistelemalla tavoitteleva hahmo).Tarkastellaan jahtaajan optimaalista strategiaa tässä tilanteessa. Jahtaajan on tärkeää saada jahdattava kiinni mahdollisimman aikaisessa vaiheessa tämän pakomatkaa, jotta esineen saaminen itselle on mahdollisimman todennäköistä. Työn tavoitteena onkin löytää tässä tilanteessa paras reitti jahtaajalle.

## Toteutus
### Ohjelman syötteet ja tulosteet
Ohjelma saa syötteenään kaksiulotteisen taulukon, jonka arvot edustavat kuljettavia ja läpikulkemattomia ruutuja, joista pelialue muodostuu. Lisäksi ohjelma saa jahtaajan ja jahdattavan koordinaatit pelialueella.



Laskennan päätteeksi algoritmi tulostaa jahtaajan koordinaatit kullakin vuorolla hänen liikkuessaan optimaalista jahtausreittiä.

### Algoritmit ja niiden aikavaativuudet
Ensimmäinen askel reitin määrittämisessä liikkuvan maalin luo on luonnollisesti määrittää kohteen kulkema reitti. Jahdattavan reitin määritys ei ole tämän työn kannalta keskeistä asiaa, joten sen aion toteuttaa yksinkertaisesti A*-haulla. Tämä A*-haun tuottama lyhyin reitti voidaan tallentaa taulukkoon, jonka indeksistä i löytyy jahdattavan sijainti liikkumisvuorolla i.

Varsinaisen ongelman ratkaisemiseen aion soveltaa kolmea eri algoritmia: leveyssuuntaista hakua (BFS), iterative deepening search (IDS) -hakua sekä iterative deepening A* (IDA) -hakua. Näistä A* ja BFS ovat tuttuja jo tietorakenteet ja algoritmit -kurssilta, mutta IDS ja IDA* ansainnevat lyhyet esittelykappaleet. Kunkin näistä algoritmeista sekä tavallisen A* -haun aika- ja tilavaativuudet on esitetty alla, kun lyhyin polku kulkee 'd' solmun kautta ja kullakin solmulla on keskimäärin 'b' naapuria.

| Algoritmi	| Aikavaativuus		| Tilavaativuus		|
|---------------|-----------------------|-----------------------|
| A*		| O(b<sup>d</sup>)[1]	| O(b<sup>d</sup>)[1]	|
| BFS		| O(b<sup>d</sup>)[2]	| O(b<sup>d</sup>)[2]	|
| IDS		| O(b<sup>d</sup>)[3]	| O(d)[3]		|
| IDA*		| O(b<sup>d</sup>)[2]	| O(bd)[2]		|

#### IDS
IDS-haku muistuttaa huomattavasti leveyssuuntaista hakua, mutta sen tilavaativuus on ainoastaan lineaarinen. Tämä saavutetaan rajoittamalla muistissa pidettävien polkujen määrä yhteen kerrallaan tutkimalla polkuja yksitellen aloittaen lyhyimmistä ja pidentäen vähän kerrallaan. Tavallaan kyseessä on syvyysrajoitettu BFS, joka on kääritty silmukkaan, joka kasvattaa askeleittain suurinta sallittua syvyyttä, jolle haku ulotetaan. [2]

Polkujen tutkiminen aloitetaan korkeintaan syvyyteen 1 ulottuvista poluista tutkimalla rekursiivisesti kaikki ne solmut, joihin pääsemiseksi vaadittu kustannus on korkeintaan 1. Mikäli maalisolmua ei löydy, unohdetaan kaikki aiemmin löydetyt polut, kasvatetaan maksimisyvyys arvoon 2 ja suoritetaan haku uudelleen. Seuraavaksi tutkitaan syvyyteen 3, 4, 5 jne. ulottuvat polut. Tätä jatketaan, kunnes polku maalisolmuun löytyy. Näin muistissa on kerrallaan vain yksi polku, mutta alkumatkan solmut tutkitaan useilla eri D:n arvoilla. [2]

#### IDA*
IDA* muistuttaa hyvin paljon IDS-hakua, mutta huomioon otetaan solmuun x pääsemiseksi vaadittujen askeleiden g(x) lisäksi solmusta maaliin pääsyn kustannusta arvioiva heuristiikka h(x). Näistä saadaan kustannusarvio f(x) = g(x) + h(x) vastaavasti kuin A* -haussa. Nyt haku aloitetaan solmuista, joiden kustannusarvio maalisolmuun on h(alku) ja aina kun kaikki edellisen maksimikustannuksen sallimat solmut on laajennettu, valitaan seuraavaksi suurimmaksi sallituksi kustannukseksi pienin naapurisolmuista löydetty f(x):n arvo.

#### Muutokset algoritmeissa
Liikkuvan maalin luo navigointi vaatii kuitenkin joitakin muutoksia tavanomaisiin reitinhakualgoritmeihin, sillä maalisolmu vaihtuu aina kun haku etenee syvemmälle, sillä jokaista jahtaajan ottamaa askelta kohden jahdattava liikkuu myös.

BFS-hakuun tämä on helppoa toteuttaa ainoastaan muuttamalla tarkastusta, jolla tutkitaan, onko tutkittava solmu maalisolmu. Sen sijaan, että solmua verrattaisiin tiettyyn yhteen solmuun, valitaan jahdattavan polkua kuvaavasta taulukosta se solmu, jonka indeksi on sama kuin tutkittavan solmun etäisyys lähtösolmusta. Vaikka polkua ei olisi löytynyt, haku voidaan lopettaa kun jahdattava pääsee maaliin.

IDS-hakuun voidaan maalisolmun vaihtuminen toteuttaa vastaavalla tavalla käyttämällä maalisolmuna maksimihakusyvyyden osoittamaa solmua. IDA* -haussa voidaan toimia vastaavasti kun myös heuristiikkafunktion arvoja laskiessa otetaan huomioon maalin siirtyminen.


## Lähteet
[1] Russell S, Norbig P: Artificial Intelligence: A Modern Approach

[2] Alan Mackworth, University of British Colubia CS 322 -kurssin luentokalvot http://www.cs.ubc.ca/~mack/CS322/lectures/2-Search6.pdf

[3] Korf, R: Dept-First Iterative Deepening: An Optimal Admissible Tree Search. Luettavissa internetissä osoitteessa https://cse.sc.edu/~mgv/csce580f09/gradPres/korf_IDAStar_1985.pdf
