import { Injectable } from '@angular/core';
declare var SockJS;
declare var Stomp;

///////////////////////////////////////////////////////////////////////////////////////////////////////////

////////////////GLI STESSI URL DEVONO COINCIDERE NEL SERVER 
////////////BROKERS
var URL_SERVER_BROCKER_POINT_TOPIC          = "/topic";
var URL_SERVER_BROCKER_POINT_MESSAGGE       = "/message";
var URL_SERVER_BROCKER_POINT_QUEUE          = "/queue/";
var URL_SERVER_BROCKER_POINT_CHAT_INIT      = "/chat/init";
var URL_SERVER_BROCKER_POINT_PAYLOAD        = "/payload";



////////////QUI GLI URL_SERVER_BROCKER_POINT SONO PRECEDUTI DA URL FATTI A CASO CIOE' A RANDOM!!! 
var SEND_END_POINT_SERVER_MESSAGE     = "/send"              + URL_SERVER_BROCKER_POINT_MESSAGGE;
var SEND_END_POINT_SERVER_TOPIC       = "/endpoint/recive"   + URL_SERVER_BROCKER_POINT_TOPIC ;
var SEND_END_POINT_SERVER_PAYLOAD     = "/news"              + URL_SERVER_BROCKER_POINT_PAYLOAD ;

var APP_DESTINATION_PREFIXES = "/app";

///////////////////////////////////////////////////////////////////////////////////////////////////////////





@Injectable({
  providedIn: 'root'
})





export class MessageService {


  constructor() {
    this.initializeWebSocketConnection(); /////// INIT WEBSOCKET
  }
  public  stompClient;
  public  msg = [];


  initializeWebSocketConnection() {
    const serverUrl = 'http://localhost:8876/socket';
    const ws = new SockJS(serverUrl);
    this.stompClient = Stomp.over(ws);
    const that = this;
    // tslint:disable-next-line:only-arrow-functions
    this.stompClient.connect({}, function(frame) {
      //////////////////IL CLIENT SI ISCRIVE GRAZIE AL BROKER CREATO NEL BACK-END !!
      that.stompClient.subscribe(URL_SERVER_BROCKER_POINT_TOPIC, (unMessaggioQualsiasi) => { //////<<<<---- in base al broker TOPIC, MESSAGE ecc..
        if (unMessaggioQualsiasi.body) {
          that.msg.push(unMessaggioQualsiasi.body);
        }
      });
    });
  }

  sendMessage(message) {
    var URL_END_POINT_MESSAGE    = APP_DESTINATION_PREFIXES + SEND_END_POINT_SERVER_MESSAGE ;
    var URL_END_POINT_TOPIC      = APP_DESTINATION_PREFIXES + SEND_END_POINT_SERVER_TOPIC;
    var URL_END_POINT_PAYLOAD    = APP_DESTINATION_PREFIXES + SEND_END_POINT_SERVER_PAYLOAD;
  //  this.stompClient.send(URL_END_POINT_MESSAGE , {}, message); //////<<<<---- in base al broker
     this.stompClient.send( URL_END_POINT_TOPIC, {}, message);  //////<<<<---- in base al broker
  }
}
