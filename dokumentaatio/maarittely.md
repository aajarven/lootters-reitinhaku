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
Liikkuvan maalin luo navigointi vaatii joitakin muutoksia tavanomaisiin reitinhakualgoritmeihin. 
