package org.rosedu.dandroid.messageme.entities;

public class Message {
    private int messageId;
    private String sender;
    private String receiver;
    private String subject;
    private String content;
    private String timestamp;
    private String status;

    public Message() {
        this.messageId = -1;
        this.sender = null;
        this.receiver = null;
        this.subject = null;
        this.content = null;
        this.timestamp = null;
        this.status = null;
    }

    public Message(int messageId, String sender, String receiver, String subject, String content, String timestamp, String status) {
        this.messageId = messageId;
        this.sender = sender;
        this.receiver = receiver;
        this.subject = subject;
        this.content = content;
        this.timestamp= timestamp;
        this.status = status;
    }

    public int getMessageId() {
        return messageId;
    }

    public void setMessageId(int messageId) {
        this.messageId = messageId;
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public String getReceiver() {
        return receiver;
    }

    public void setReceiver(String receiver) {
        this.receiver = receiver;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
