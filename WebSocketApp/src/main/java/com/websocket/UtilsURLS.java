/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.websocket;

/**
 *
 * @author marco
 */
public class UtilsURLS {
    
    
    public static final String APP_DESTINATION_PREFIXES = "/app";
    public static final String END_POINT_APPLICATION    = "/socket";
    
    
///////////////////////////// URLS CHE SONO INSERITE NELLA CONFIC DI WEB SOCKET E CHE I CLIENT SI DEVONO ISCRIVERE PER INVIARE E RICEVERE MESSAGGI..!!
    public  static final String URL_SERVER_BROCKER_POINT_TOPIC          = "/topic";
    public  static final String URL_SERVER_BROCKER_POINT_MESSAGGE       = "/message";
    public  static final String URL_SERVER_BROCKER_POINT_QUEUE          = "/queue/";
    public  static final String URL_SERVER_BROCKER_POINT_CHAT_INIT      = "/chat/init";
    public  static final String URL_SERVER_BROCKER_POINT_PAYLOAD        = "/payload";


    /////////////////// IMPORTANTE : QUESTE URL SONO MESSE ALLA FINE DI OGNI URL NEGLI ENDPOIN DEI CONTROLLER. 

}
