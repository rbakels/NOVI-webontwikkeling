# Bouwen van het project.
Bouwen van het project werkt via Maven. Maven kun je op elke PC installeren. Als je dat hebt gedaan, dan kun je het volgende commando gebruiken om het te bouwen:

    mvn package

Binnen de target/ directory van elk submodule vind je een .ear, .war of .jar bestand. De .ear of .war dient te worden geinstalleerd op een Applicatie Server. Tomcat EE of Glassfish zijn gratis beschikbaar. Let op, het moet Java EE 7 ondersteunen.

# Brondata
De brondata vind je op: https://gist.github.com/rbakels/6e14494f135eb7f87699a0d63d0e59ed

In de code staat een verwijzing naar Localhost. Dit vanwege security redenen op mijn eigen laptop. Om een lokale server op te zetten dien je een 'http-server' te installeren, via NPM:

    npm install http-server -g

Daarna kun je het volgende commando gebruiken om het te draaien, binnen de root van het project:

    http-server web

De bestanden die nodig zijn staan in de root directory onder de web directory.    