# Viikkoraportti 1 (10 h)
## Viikon aikaansaannokset
* Github-repon luonti
* Ongelman rajaamista, liikkumistekoälyn rajojen hahmottelua
* Algoritmivaihtoehtojen selvittelemistä
* Algoritmien valinta
* Algoritmeihin maalin liikkumisen huomioon ottamiseksi tarvittavien muutosten miettiminen

## Ohjelman edistyminen
Ei edistystä

## Mitä opin
* IDS- ja IDA*-algoritmien olemassaolon
* että paperin voi otsikoida "time complexity of _______" ilman että oikeasti kertoo kyseisen algoritmin aikavaativuutta

## Mikä oli vaikeaa tai jäi epäselväksi
* Ongelman rajaaminen sellaiseksi ettei se räjähdä käsiin
..* Jos samassa ruudussa voi olla vain yksi hahmo ja tukin jahdattavan hahmon reitin, menevät reitit uusiksi, pahimmassa tapauksessa deadlock jossa kukaan ei koskaan pääse mihinkään
..* Entä jos lyhyin reitti ei ole yksiselitteinen (päädyin olemaan ottamaan tähän varsinaisesti kantaa, kyseessä on kuitenkin tekoäly vs tekoäly -tilanne, jossa sama algoritmi määrää sekä jahdattavan kulun että jahtaajan oletuksen jahdattavan kulusta eli varsinaista ongelmaa ei ole. AI vs ihminen -tilanteessa ihminen puolestaan saattaa olla muuten vain arvaamaton)
* Ovatko valitsemani algoritmit oikeasti järkevä paletti
* Toimivatko ajattelemani modifikaatiot liikkuvalle maalille soveltamiseen oikeasti. Mitään oikeellisuustodistuksiahan minulla ei ole esittää, mutta toisaalta muutokset ovat melko maalaisjärkeviä ja yksinkertaisia.

## Mitä seuraavaksi
1. Jonkinlainen pelialueen ruutua kuvaava luokka
2. A*-haku jahdattavan polun selvittämiseksi
3. Leveyshaun aloittelua
