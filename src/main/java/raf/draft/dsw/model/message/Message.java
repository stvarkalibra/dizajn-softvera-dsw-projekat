package raf.draft.dsw.model.message;

import java.time.LocalDateTime;

public class Message {
    private String content;
    private MessageType type;
    private LocalDateTime timeStamp;

    public Message(String content, MessageType type, LocalDateTime timeStamp) {
        this.content = content;
        this.type = type;
        this.timeStamp = timeStamp;
    }

    @Override
    public String toString() {
        return "[" + type + "]" + " [" + timeStamp + "]" + " " + content;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public MessageType getType() {
        return type;
    }

    public void setType(MessageType type) {
        this.type = type;
    }

    public LocalDateTime getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(LocalDateTime timeStamp) {
        this.timeStamp = timeStamp;
    }
}
