package raf.draft.dsw.model.message;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.SimpleTimeZone;

public class Message {
    private String content;
    private MessageType type;
    private Date timeStamp;

    public Message(String content, MessageType type) {
        this.content = content;
        this.type = type;
        this.timeStamp = new Date();
    }

    @Override
    public String toString() {
        SimpleDateFormat simpleDateFormat=new SimpleDateFormat("dd.MM.yyyy. HH:mm");
        String formattedTimeStamp=simpleDateFormat.format(timeStamp);
        return "[" + type + "]" + " [" + formattedTimeStamp + "]" + " " + content;
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

    public Date getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(Date timeStamp) {
        this.timeStamp = timeStamp;
    }
}
