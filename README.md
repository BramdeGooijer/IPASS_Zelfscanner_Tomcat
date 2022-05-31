1	Inleiding 
Het idee van het maken van een zelfscanner voor het project IPASS is tot stand gekomen nadat ik iets wilde doen met de Tikkie API. Maar alleen het gebruiken van deze API zou niet heel erg interessant zijn dus heb ik er bij bedacht om ook barcodes te kunnen scannen wat mij vervolgens op het idee bracht om een zelfscanner te maken. De zelfscanner is bedoeld voor de doelgroep klanten die winkelen bij bijvoorbeeld een supermarkt.
2	Overzicht 
De IPASS zelfscanner die ik in dit project zal maken heeft de functie om gemakkelijk en snel producten te kunnen scannen en daarna af te rekenen. De klant verzamelt als het ware zijn of haar producten en scant deze vervolgens bij de zelfscanner, daarna kan er nog eventuele aanpassingen gedaan worden aan de producten zoals het verwijderen van een product van de lijst of het verhogen of verlagen van het aantal producten. Als dit is gedaan klikt de klant op betalen en zal er betaald moeten worden voor de producten.
3	Use cases
 

Hier boven ziet u het use case diagram voor de IPASS zelfscanner. Buiten alle voor de hand liggende use cases zoals in- en uitloggen hebben de volgende use cases de volgende betekenis:
•	Product scannen: Het product van de klant moet gescand kunnen worden en vervolgens moet de prijs, hoeveelheid en omschrijving (naam) van het product toegevoegd worden aan het winkelmandje.
•	Product aantal wijzigen: Het aantal van een bepaald product moet gewijzigd kunnen worden zodat een klant bijvoorbeeld een product niet vaker hoeft te scannen en hij of zij gewoon het aantal handmatig kan invullen.
•	Product verwijderen: Een product dat foutief gescand is moet van het boodschappenlijstje afgehaald kunnen worden.
•	Transactie maken: De klant moet voor de producten kunnen betalen door middel van het scannen van een qr-code betaald de klant met tikkie snel voor zijn of haar producten.
•	Producten controleren: Voor het tegengaan van diefstal zal er af en toe gecontroleerd moeten worden of de klant alle producten wel heeft gescand.
•	Geautoriseerde wijzigingen maken (prijs van een product wijzigen): De medewerker zal met een speciale code extra wijzigingen moeten kunnen uitvoeren op producten zoals het wijzigen van de prijs waar de klant geen toegang tot heeft.
3.1	Actoren
Klant: De actor klant zal in het proces van de zelfscanner producten scannen, aantallen wijzigen, ongewilde producten verwijderen van de boodschappen lijst, en vervolgens de betaling van de producten doorlopen.

Medewerker: De rol van de actor medewerker in het proces van de zelfscanner zal het opstarten van de zelfscanner zijn, maar ook het uitvoeren van (geautoriseerde) wijzigingen zoals het veranderen van het aantal producten of het veranderen van de prijs van een bepaald product.


Use case	Actor	Description (role description)
In- en uitloggen	Medewerker	De medewerker moet, om het programma op te starten en af te sluiten, kunnen in en uitloggen zodat de zelfscanner door de klant gebruikt kan worden.
Product scannen	Klant	De klant moet zijn of haar producten kunnen scannen om ervoor te kunnen betalen.
Product aantal wijzigen	Klant, Medewerker	De klant moet het aantal van het product kunnen wijzigen.
De medewerker moet dit ook kunnen doen voor de klant.
Producten verwijderen van boodschappenlijstje	Klant, Medewerker	De klant en de medewerker moet producten kunnen verwijderen van de boodschappenlijst wanneer er bijvoorbeeld een product verkeerd is gescand.
Transactie maken	Klant	De klant moet kunnen betalen voor de gescande producten.
Producten controleren	Medewerker	De medewerker zal de producten van een klant controleren om te kijken of alles wel gescand is.
Geautoriseerde wijzigingen uitvoeren.	Medewerker	De medewerker moet geautoriseerde wijzigingen uit kunnen voeren zoals bijvoorbeeld het wijzigen van de prijs van een product.


 
3.2	Use case templates
Use case templates, per use case een paragraaf met de template beschrijving e.d. zoals geleerd bij Modelling.

Use case	In- en uitloggen
Samenvatting	Er moet in en uitgelogd kunnen worden op de zelfscanner
Actor	Medewerker
Stappenplan	1.	De actor voert gebruikers naam en / of wachtwoord in
2.	De actor klikt op inloggen
De actor klikt op uitloggen

Of

1.	De actor drukt op uitloggen

Error: foutcode inloggegevens onjuist
Postconditie	Er is in of uitgelogd

Use case	Product scannen
Samenvatting	De actor moet een product kunnen scannen
Actor	Klant
Stappenplan	1.	De actor pakt een product
2.	De actor houdt deze voor de scanner
3.	Het product is gescand

Error: Er komt een foutcode vanwege bijvoorbeeld een onbekend product
Postconditie	Het product is gescand

Use case	Product aantal wijzigen
Samenvatting	Het aantal van een product moet gewijzigd kunnen worden
Actor	Medewerker & klant
Stappenplan	1.	De actor drukt op een product
2.	De actor vult de hoeveelheid in
3.	De actor drukt op enter om de aangepaste hoeveelheid te bevestigen
Postconditie	Het aantal is gewijzigd

Use case	Product verwijderen uit de boodschappenlijst
Samenvatting	Producten moeten verwijderd kunnen worden van de boodschappenlijst
Actor	Medewerker, klant
Stappenplan	1.	De actor selecteerd het product
2.	De actor drukt op de knop voor het verwijderen van het product
Postconditie	Het product is verwijderd

 
Use case	Transactie maken
Samenvatting	De actor moet kunnen betalen voor zijn of haar producten
Actor	Klant
Stappenplan	1.	De actor drukt op betaal
2.	De actor scant de barcode
3.	De actor betaald met zijn of haar bank
4.	De betaling is gedaan

Error: Er is iets fout gegaan tijdens het betalen (bijv. betaling afgesloten).
Postconditie	Er is betaald

Use case	Producten controleren
Samenvatting	De actor moet de producten van de klant kunnen controleren zodat hiermee diefstal wordt voorkomen
Actor	Medewerker
Stappenplan	1.	De actor krijgt een melding
2.	De actor controleert of alle producten gescand zijn
3.	De actor keurt de betaling goed
Postconditie	De producten zijn gecontroleerd

Use case	Prijs van een product wijzigen (of andere geautoriseerde wijzigingen)
Samenvatting	De actor moet de prijs (of andere geautoriseerde wijzigingen) kunnen veranderen
Actor	Medewerker
Stappenplan	1.	De actor vult een speciale toegangscode in
2.	De actor voert de wijziging uit
3.	De actor sluit het scherm met geautoriseerde toegang
Postconditie	Er is een geautoriseerde wijziging uitgevoerd

4	Domeinmodel
 

Entiteit	Beschrijving
Kassa	Wanneer er meerdere kassa’s zullen zijn zullen deze ook allemaal verschillende nummers moeten hebben
Medewerker	De medewerker heeft bepaald gegevens nodig om in te kunnen loggen op de kassa’s en ook moet deze extra rechten kunnen krijgen op deze kassa’s
Transactie	Hier word de informatie opgeslagen voordat deze verstuurde word naar de API. Dit is het totaaloverzicht van alle transactieregels
Transactieregel	Een enkele regel van de transactie. Dit behoud 1 soort product waarvan een aantal is vastgelegd
Product	In deze entiteit worden alle producten vastgesteld met alle bijbehorende informatie zoals de prijs barcodenummer en omschrijving
