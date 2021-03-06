# Käyttöohje
Ajettava jar-tiedosto ja luolat sijaitsevat dist-kansiossa. Ohjelma on mahdollista ajaa komennolla
<pre>java -jar reitinhaku-1.0.jar</pre>

Koska projektin tavoitteena on ainoastaan saada toteutettua osa suurempaa kokonaisuutta, ohjelma sisältää ainoastaan hyvin yksinkertaisen käyttöliittymän. Se kysyy käyttäjältä interaktiivisesti aluksi, mitä käyttäjä tahtoo tehdä. Suorituskykytestauksen valitseminen tulostaa suorituskykytestauksen tulokset (reitin pituus tai -1 mikäli reittiä ei löydy ja ohjelman suoritusvaiheisiin kuluneet ajat nanosekunteina). Huomaa, että suuremmilla syötteillä sokkelon läpäisemiseen kuluu useita sekunteja.

Reitinhaku kysyy käyttäjältä polkua käytettävään luolaan, hahmojen ja maalin sijainnit sekä käytettävän hakualgoritmin ajaen tämän jälkeen pyydetyn haun. Vääränlainen syöte johtaa ohjelman kaatumiseen. Mikäli reitti on olemassa, ohjelma tulostaa jahtaajan sijainnit muodossa "x, y" sen matkalla jahdattavan luo. Muussa tapauksessa ilmoitetaan, ettei reittiä löydy.

Luolat löytyvät jar-tiedoston kanssa samasta kansiosta polusta resources/testiluolat. Näistä muotoa n-n.txt olevat ovat sokkeloita joiden sivun pituus on n ja 80avoin.txt on 80x80 ruudun täysin avoin kartta, jonka jokainen node on kuljettavissa. Tiedostot hakuluola1.txt, testiluola1.txt ja testiluola2.txt sisältävät hyvin yksinkertaiset kartat. Muut luolat ovat jollain tavalla viallisia ja niillä on mahdollista testata ohjelman käyttäytymistä virhetilanteissa.

Karttoja on mahdollista tehdä myös itse. Luolat tallennetaan tekstitiedostoihin, joissa 1 edustaa kuljettavissa olevaa ruutua ja 0 ruutua, johon ei voi kävellä. Risuaitamerkillä (#) alkavat rivit ovat kommentteja, joita ei käsitellä luolaa luettaessa. Ensimmäisellä rivillä tulee olla kaksi kokonaislukua välilyönnillä erotettuna. Näistä ensimmäinen kertoo luolan korkeuden (y-suunta) ja jälkimmäinen leveyden (x-suunta). Tämän jälkeen annetaan varsinainen luola saman x-koordinaatin ruudut omalla rivillään välilyönnein erotettuna. Piste 0,0 on luolan vasemmassa yläkulmassa ja x- ja y-koordinaatit kasvavat oikealle ja alas.
