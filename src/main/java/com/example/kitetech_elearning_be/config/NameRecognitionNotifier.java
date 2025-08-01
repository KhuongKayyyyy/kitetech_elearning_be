package com.example.kitetech_elearning_be.config;

import com.example.kitetech_elearning_be.name_recognition.NameRecognitionDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class NameRecognitionNotifier {

    private final SimpMessagingTemplate messagingTemplate;

    public void notify(String classSessionId, NameRecognitionDTO dto) {
        String topic = "/topic/name-recognition/class-session/" + classSessionId;
        System.out.println("📢 Sending WebSocket message to topic: " + topic + " with payload: " + dto);
        messagingTemplate.convertAndSend(topic, dto);
    }
}