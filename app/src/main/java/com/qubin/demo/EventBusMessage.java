package com.qubin.demo;

/**
 * author：created by qub
 * date：2020/5/19 14
 * descri：
 */
public class EventBusMessage {

    public  String message;

    private EventBusMessage(String message){
        this.message = message;
    }

    public String getMessage(){
        return message;
    }
}
