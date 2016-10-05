# Testausdokumentti
## Testauksen laajuus ja toteutus
Ohjelman kaikkien luokkien toiminta mainia lukuunottamatta on testattu pit-mutaatiotestausta käyttäen. Rivikattavuus on hyvin lähellä sataa, ainoastaan vain staattisia metodeja sisältävistä aiheutuu muutamia "testaamattomia" rivejä sillä pit tulkitsee tällöin luokkien määrittelyrivit testaamattomiksi. Mutaatiokattavuus puolestaan on 100%. Myös pit-raportti kokonaisuudessaan on nähtävissä (dokumentaatio/pit).

### LuolaLukija
LuolaLukijan toimivuus testattiin sekä toimivilla että eri tavoin toimimattomilla, joskin pienillä syötteillä. Toimivana luolana käytettiin sisällöltään seuraavanlaista tiedostoa:
<pre>
# kommentti
2 4

1 0 1 1
1 1 1 0
</pre>
Tämän tiedoston tuottaman luolan oikeellisuus testattiin vertaamalla sitä manuaalisesti luotuun kovakoodattuun taulukkoon.

Huonot syötteet testattiin seuraavantyyppisillä luolilla:
* Tiedostossa vähemmän rivejä kuin header lupaa
* Tiedostossa vähemmän sarakkeita kuin header lupaa
* Tiedostossa enemmän rivejä kuin header lupaa
* Tiedostossa enemmän sarakkeita kuin header lupaa
* Tiedosto sisältää arvoja, jotka eivät ole lukuja eivätkä kommenttirivejä
* Tiedostoa ei ole olemassa
* Header ei sisällä kahta kokonaislukua
* Headerin sisältöä ei voida parsia kokonaisluvuiksi

### Node
Node-luokan kaikille julkisille metodeille on omat testinsä. Naapurien lisäämisen toiminta testataan varmistamalla, että vastaluodulla nodella ei ole yhtään naapuria mutta yhden naapurin lisäämisen jälkeen naapureita on yksi.

Koordinaattien getterit testataan yksinkertaisesti vertaamalla niiden palauttamia arvoja Nodea luotaessa annettuihin arvoihin. Metodin getKustannusNodeenAsti toiminta testataan luomalla kolmen noden ketju ja tarkastamalla kaikkien niiden palauttamat arvot. Kokonaiskustannuksen testaaminen suoritetaan samoin, mutta nyt ketju on vain kahden solmun mittainen ja lisäksi luodaan maalisolmu, johon kustannusta lasketaan.

Metodi yritaAsettaaEdeltaja testattiin luomalla neljä neliötä vierekkäin siten, että ne muodostavat neliön. Asetetaan neliön origoa lähin kulma ("vasen yläkulma") lähtönodeksi (nollataan sen g-funktion arvo eli solmuun pääsemiseksi vaadittava kustannus) ja yritetään lisätä se kummankin sen vieressä olevan solmun ("vasen alakulma" ja "oikea yläkulma") edeltäjäksi. Kummassakin tapauksessa metodin pitäisi palauttaa true, sillä edeltäjättömän noden tulee aina ottaa tarjottu edeltäjä. Yritetään samoin asettaa vielä edeltäjättömälle "oikean alakulman" nodelle edeltäjäksi vasemman alakulman naapurisolmu, minkä pitäisi myös onnistua. Nyt kun yritetään vielä asettaa oikean yläkulman nodelle udeksi edeltäjäksi oikean alakulman node, tulisi metodin palauttaa false, sillä yhden mittaista edeltäjäketjua ei kannata korvata kahdenmittaisella.

Lisäksi testasin equals-metodin toimintaa luomalla kolme nodea, joista kahdella oli samat koordinaatit ja varmistamalla, että nille kahdelle equals palauttaa true, mutta eri koordinaateille kutsuttuna false. Lopuksi testasin, että equals kutsuttuna nodelle ja jonkin muun luokan oliolle palauttaa false. Lisäksi testasin, että hashcodet ovat samat, mikäli nodet ovat samat. En kuitenkaan testannut hashCodejen törmäyksien yleisyyttä tai muutenkaan tapauksia, joissa hashcodet saattavat poiketa toisistaan.

### NaapuriLista

NaapuriListasta tarkastetaan, että vastaluotu lista on tyhjä. Lisäksi varmistetaan, että kun listalle lisätään uusi alkio, listan pituus kasvaa, mutta jo listalla olevan alkion lisääminen uudestaan ei kasvata listan pituutta.

### NodeGeneraattori
NodeGeneraattorin testaamiseen käytän seuraavaa luolaa:
<pre>
# kommentti
7 4

1 0 1 1
1 1 1 0
0 1 1 1
1 0 1 0
1 0 1 1
0 0 1 1
1 1 1 0
</pre>
Tämän luolan suunnittelin sellaiseksi, että siinä on eristyksissä olevia nodeja (joita varsinaisen pelin luolastosta ei mitä suurimmalla todennäköisyydellä tosin tule löytymään), sekä nodeja joilla on yksi, kaksi, kolme tai neljä naapuria. 

Luodusta luolasta testaan luolan oikean koon, kulmissa olevian nodejen naapurimäärät sekä naapurimäärät yhdeltä kunkin odotetun naapurimäärän nodelta. Lisäksi tarkastan, ettei kulkukelvottomasta ruudusta ole luotu Node-oliota. Naapurien oikeellisuuden testaan testaamalla, että nodeilla, jotka ovat toistensa ainoat naapurit, naapurin naapurin hakeminen palauttaa alkuperäisen noden.

### JonoAlkio
JonoAlkiosta testataam seuraajan asettamisen ja hakemisen toiminnan tarkastamalla ensin, että vastaluodun JonoAlkion seuraaja on null, sen jälkeen asettamalla sille seuraajan ja varmistamalla, että getSeuraaja palauttaa äsken asetetun noden. Lisäksi testasin, että getNode-metodi palauttaa saman noden kuin JonoAlkiolle on sitä luotaessa annettu.

### Jono
Jonosta testataan, että alkion lisäämisen jälkeen jono ei enää ole tyhjä ja että jonoon lisätyt alkiot palautetaan samassa järjestyksessä kuin ne on jonoon laitettu. Samoin testataan, että juuri luodun jonon onTyhjä-metodi palauttaa true, yhden alkion lisäämisen jälkeen false ja kun kaikki alkiot on poimittu jälleen true. Lisäksi varmistetaan, että tyhjälle jonolle kutsuttaessa poimiNode-metodi palauttaa null.

### PrioriteettijonoAlkio
PrioriteettijonoAlkion testaus muistuttaa hyvin paljon JonoAlkion testausta. Jo JonoAlkiossa esiteltyjen testitapausten lisäksi testataam, että alkiot palauttavat oikean kustannuksen (alkion edustamaan nodeen pääsemiseksi vaadittu kustannus + heuristiikka)

### Prioriteettijono
Prioriteettijonosta testataan, että kun jonoon laitetaan yksi node ja sitten poimitaan se, saadaan takaisin sama node kuin jonoon laitettiin. Jonon tyhjyyden tarkastaminen on toteutettu samoin kuin tavallisella jonolla.

Lisäksi tarkastetaan, että kun jonoon laitetaan useita nodeja satunnaisessa, palauttaa poimiNode-metodi niitä sellaisessa järjestyksessä, että seuraavan noden kokonaiskustannus-metodi palauttaa vähintään yhtä suuren luvun kuin edellisen. Erikseen testataan, että jonon ensimmäisen alkion vaihtaminen toimii lisäämällä tyhjään jonoon ensin yksi alkio ja sen jälkeen toinen, jonka kustannus on pienempi. Lisäksi testataan lisäämällä jonoon sama alkio useita kertoja ja varmistamalla, että jono on tyhjä yhden poiston jälkeen, että jono ei tallenna duplikaatteja. Samaa testataan vielä lisäämällä jonoon vaihtelevassa järjestyksessä kahta eri nodea vaihtelevassa järjestyksessä, poimimalla node kahdesti ja tarkastamalla jonon tyhjyys.

###AStar


## Testien toistettavuus

## Graafinen muoto önnönnöö
