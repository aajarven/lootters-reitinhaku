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

### NodeGeneraattori

### JonoAlkio

### NodeJono

###AStar


## Testien toistettavuus

## Graafinen muoto önnönnöö
