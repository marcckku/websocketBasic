# websocketBasic
Applicazione Basica di come creare una piccola Chat tra un Server e un Client Remoto.

IMPORTANTE! > Viene utilizzato ...setAllowedOrigins("*") che abilita la comunicazione tra ORIGINI DIVERSI!!...

+ Server fatto in SpringBoot versione 2.3.3.RELEASE. Per verificare che tutto sia in ordine, utilizza POSTMAN e inserisci "http://localhost:8876/socket" 
l'output atteso è: "Welcome to SockJS!".


+ Client fatto in Angular 2.x. Per eseguire il server del Client Angular fare [ ng serve ]. Di solito Angular crea l'url http://localhost:4200/
Il client si dovrà collegare su  "http://localhost:8876/socket" poi in console del medesimo browser dovrai vedere :

Web Socket Opened...\
stomp.min.js:8 >>> CONNECT\
accept-version:1.1,1.0\
heart-beat:10000,10000\

Vuol dire che il collegamento esiste!!! altrimenti non sarà possible il collegamento..!\


////////////////////////////////
…or push an existing repository from the command line \
git remote add origin https://github.com/marcckku/websocketBasic.git  \
git branch -M master \
git push -u origin master \
////////////////////////////////
