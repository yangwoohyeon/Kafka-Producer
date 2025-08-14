package com.example.emailsendproducer;

public class EmailSendMessage { //카프카로 보내는 Message 객체
    private String from; //발신자 이메일
    private String to; //수신자 이메일
    private String subject;  //이메일 제목
    private String body;  //이메일 본문

    public EmailSendMessage(String body, String to, String subject, String from) {
        this.body = body;
        this.subject = subject;
        this.to = to;
        this.from = from;
    }

    public String getFrom() {
        return from;
    }

    public String getTo() {
        return to;
    }

    public String getSubject() {
        return subject;
    }

    public String getBody() {
        return body;
    }
}
