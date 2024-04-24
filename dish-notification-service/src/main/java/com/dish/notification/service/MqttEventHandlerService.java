//package com.dish.notification.service;
//
//import org.eclipse.paho.client.mqttv3.IMqttClient;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//@Service
//public class MqttEventHandlerService {
//
//    @Autowired
//    private IMqttClient mqttClient;
//
//    public void handleMqttEvent(String topic, String message) {
//        // Implement your MQTT event handling logic here
//        System.out.println("Received message on topic: " + topic + ", message: " + message);
//    }
//}