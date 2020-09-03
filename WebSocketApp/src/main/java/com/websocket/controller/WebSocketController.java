/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.websocket.controller;

import com.websocket.UtilsURLS;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author marco
 */




@RestController
public class WebSocketController {

    private final SimpMessagingTemplate simpMessagingTemplate;
    
  // SE CAMBIA L'URL QUI ALLORA ANCHE NEL CLIENT DEBE CAMBIARE!!. DEVONO ESSERE UGUALI GLI URL,
  // PERCHE E' IL NOME SUL QUALE IL CLIENT SI SOTTOSCRIBE QUINDI RICEVE E MANDA MESSAGGI.
    
    
    /////////PUOI INSERIRE QUALSIASI TIPO DI URL (INVENTATE) QUI
    final String PRE_URL_A_RANDOM_01 =   "/send";
    final String PRE_URL_A_RANDOM_02 =  "/endpoint/recive";
    final String PRE_URL_A_RANDOM_03 =  "/news" ;
    
    
    
    
    
    
    ///////////// LE URL (INVENTATE) DEVONO PRECEDERE LA  URL_SERVER_BROCKER_POINT
    final String URL_END_POINT_MESSAGE  = PRE_URL_A_RANDOM_01    + UtilsURLS.URL_SERVER_BROCKER_POINT_MESSAGGE;
    final String URL_END_POINT_TOPIC    = PRE_URL_A_RANDOM_02    + UtilsURLS.URL_SERVER_BROCKER_POINT_TOPIC;
    final String URL_END_POINT_PAYLOAD  = PRE_URL_A_RANDOM_03    + UtilsURLS.URL_SERVER_BROCKER_POINT_PAYLOAD ;
    
    
    
   
    
    @Autowired
    WebSocketController(SimpMessagingTemplate template){
        this.simpMessagingTemplate = template;
    }
    
    
    
   
    
        
    /**
      @MessageMapping("/news")
    @SendTo("/topic/news")
    public ResponseEntity<String> broadcastNews(@Payload String message) {
      //return message;
         return  ResponseEntity.ok(content);
    }

    */
    
    //////////////////////// END POINTS ////////////////////
    

    //////// ENDPOINT RECIVE - FROM REMOTE ORIGIN *
    @MessageMapping(value = { URL_END_POINT_MESSAGE })
    public void reciveRemoteMessege(String message){
         System.out.println("\n ##### ENDPOINT URL #### \n \t" + URL_END_POINT_MESSAGE);
         System.out.println("\n ##### ENDPOINT RECIVE - FROM REMOTE ORIGIN #### \n \t" + message);        
         this.simpMessagingTemplate.convertAndSend(UtilsURLS.URL_SERVER_BROCKER_POINT_MESSAGGE,  message);
    }
    
    
    
    
    //////// ENDPOINT RECIVE - FROM REMOTE ORIGIN *
    @MessageMapping(value = { URL_END_POINT_TOPIC })
    public void reciveRemoteMessegeTopic(String message){
         System.out.println("\n ##### ENDPOINT URL #### \n \t" + URL_END_POINT_TOPIC);
         System.out.println("\n ##### ENDPOINT RECIVE - FROM REMOTE ORIGIN #### \n \t" + message);        
         this.simpMessagingTemplate.convertAndSend(UtilsURLS.URL_SERVER_BROCKER_POINT_TOPIC , message);
    }
    
    
    
    @MessageMapping(URL_END_POINT_PAYLOAD)
    public void broadcastNewsPayload(@Payload  String msg) {
         System.out.println("\n ##### ENDPOINT URL #### \n \t" + URL_END_POINT_PAYLOAD);
         System.out.println("\n ##### ENDPOINT RECIVE - FROM REMOTE ORIGIN #### \n \t" + msg);
         this.simpMessagingTemplate.convertAndSend(UtilsURLS.URL_SERVER_BROCKER_POINT_PAYLOAD, msg);
    }


}