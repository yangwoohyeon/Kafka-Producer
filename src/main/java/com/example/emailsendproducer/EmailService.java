package com.example.emailsendproducer;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class EmailService {
    private final KafkaTemplate<String, String> kafkaTemplate; //카프카로 메시지를 보내는 객체

    public EmailService(KafkaTemplate<String, String> kafkaTemplate){
        this.kafkaTemplate=kafkaTemplate;
    }

    public void sendEmail(SendEmailRequestDto request){ //DTO를 EmailSendMessage 객체로 변환
        EmailSendMessage emailSendMessage = new EmailSendMessage(
            request.getFrom(),
            request.getTo(),
            request.getSubject(),
            request.getBody()
        );

        this.kafkaTemplate.send("email.send",toJsonString(emailSendMessage));
    }
    private String toJsonString(Object object) { //객체를 JSON 문자열로 직렬화 (카프카의 key-value 형식이 String형식이기 때문)
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            String message = objectMapper.writeValueAsString(object);
            return message;
        }catch(JsonProcessingException e){
            throw new RuntimeException("Json 직렬화 실패");
        }
    }
}
