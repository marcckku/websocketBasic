/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.websocket;

/**
 *
 * @author marco
 * 
 * 
 * WebSocketConfig è annotato con @Configuration per indicare che si tratta di 
 * una classe di configurazione Spring. È anche annotato con @EnableWebSocketMessageBroker. 
 * Come suggerisce il nome, @EnableWebSocketMessageBroker abilita la gestione 
 * dei messaggi WebSocket, supportata da un broker di messaggi.
 * 
 */



import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.AbstractWebSocketMessageBrokerConfigurer;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;



/** configureMessageBroker(MessageBrokerRegistry config) {
 
 Il metodo configureMessageBroker() implementa il metodo predefinito in 
 WebSocketMessageBrokerConfigurer per configurare il broker di messaggi. 
 
 Inizia chiamando enableSimpleBroker() per abilitare un semplice broker 
 di messaggi basato sulla memoria per riportare i messaggi di saluto al 
 client su destinazioni con prefisso  /topic. 
 
 
 Indica anche il prefisso /app per i messaggi associati a metodi annotati 
 con @MessageMapping.
 
 
 Questo prefisso verrà utilizzato per definire tutte le mappature dei messaggi. 
 
 
 Ad esempio, /app/hello è l'endpoint che il metodo GreetingController.greeting() 
 è mappato per gestire.
 
 
 **/



/** public void registerStompEndpoints(StompEndpointRegistry registry) {
 
Il metodo registerStompEndpoints() registra l'endpoint  /gs-guide-websocket, 
abilitando le opzioni di fallback SockJS in modo che possano essere utilizzati 
trasporti alternativi se WebSocket non è disponibile. Il client SockJS tenterà 
di connettersi a /gs-guide-websocket e utilizzerà il miglior trasporto 
disponibile (websocket, xhr-streaming, xhr-polling e così via).
* 

 */





////////////////////////////////////////////////////////////////////////////////////

/**
 *
 * @author marco
 * 
 * 
 * NOZIONI DA QUESTA PAG ------>>> https://www.toptal.com/java/stomp-spring-boot-websocket
 * 
 * 
 * 1) Modello pub-sub dovrebbero essere precedute da "topic" (topic in inglese sarebbe l'argomento di conversazione!!)
 * 
 * 2) D'altra parte, le destinazioni per i messaggi privati ​​sono generalmente precedute da "queue".
 * 
 * 3) prefisso "app" utilizzato per filtrare le destinazioni gestite dai metodi annotati con 
 * @MessageMapping il cui implementerai in un controller. Il controller, dopo aver elaborato 
 * il messaggio, lo invierà al broker.
 * 
 * 
 * 4) withSockJS(), abilita le opzioni di fallback di SockJS. 
 * Per farla breve, consentirà ai nostri WebSocket di funzionare anche se il protocollo 
 * WebSocket non è supportato da un browser Internet.
 * 
 * 
 * 5) C'è ancora una cosa che deve essere chiarita: perché chiamiamo il metodo 
 * " setAllowedOrigins(*)" sull'endpoint. È spesso richiesto perché il comportamento 
 * predefinito di WebSocket e SockJS è accettare solo richieste della stessa origine. 
 * Quindi, se il tuo client e il lato server utilizzano domini diversi, questo metodo 
 * deve essere chiamato per consentire la comunicazione tra di loro(CROSS-ORIGIN).
 * 
 * 
 * 
 * 6) Passaggio 3 . Implementa un controller che gestirà le richieste degli utenti. 
 * Trasmetterà il messaggio ricevuto a tutti gli utenti iscritti a un determinato 
 * argomento. Ecco un metodo di esempio che invia messaggi alla destinazione /topic/news.
 * 
 * 
 * ////O COSI 1
    @MessageMapping("/news")
    @SendTo("/topic/news")
    public void broadcastNews(@Payload String message) {
      return message;
   }
 * 
 * 
 * 
 * Invece dell'annotazione @SendTo, puoi anche usare la SimpMessagingTemplatequale  autowire all'interno del tuo controller.
 *    @Autowired
 *  private final SimpMessagingTemplate simpMessagingTemplate;
 * 
 * ///////O COSI 2
    @MessageMapping("/news")
    public void broadcastNews(@Payload String message) {
      this.simpMessagingTemplate.convertAndSend("/topic/news", message)
    }
 
                fine..
 ////////////////////////////////////////////////////////////////////
 
 
 Creazione del client WebSocket
   client fatto con Angular 2 
 * 
 * 
 * 
 * 
 */



/// link to tutorial man ufficiale spring : --->>>>  https://spring.io/guides/gs/messaging-stomp-websocket/

///Esempio ispirato da --->>>>  https://medium.com/@haseeamarathunga/create-a-spring-boot-angular-websocket-using-sockjs-and-stomp-cb339f766a98
@Configuration
@EnableWebSocketMessageBroker
public class WebSocketConfig extends AbstractWebSocketMessageBrokerConfigurer {
    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        registry.addEndpoint(UtilsURLS.END_POINT_APPLICATION) // ENDPOINT DI PARTENZA 
                .setAllowedOrigins("*") ///abilita la comunicazione tra ORIGINI DIVERSI
                .withSockJS();
    }
    @Override
    public void configureMessageBroker(MessageBrokerRegistry registry) {
         //////// message è il "topic" cioè il tipo di argomentazione sul quale un Client deve sottoscriversi!!.
         registry.setApplicationDestinationPrefixes(UtilsURLS.APP_DESTINATION_PREFIXES)
                 .enableSimpleBroker(
                         UtilsURLS.URL_SERVER_BROCKER_POINT_TOPIC,  
                         UtilsURLS.URL_SERVER_BROCKER_POINT_MESSAGGE, 
                         UtilsURLS.URL_SERVER_BROCKER_POINT_QUEUE,
                         UtilsURLS.URL_SERVER_BROCKER_POINT_PAYLOAD
                 ); 
    }
}