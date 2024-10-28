package raf.draft.dsw.controller.messagegenerator;

import raf.draft.dsw.controller.observer.IPublisher;
import raf.draft.dsw.controller.observer.ISubscriber;
import raf.draft.dsw.model.message.Message;
import raf.draft.dsw.model.message.MessageType;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class MessageGenerator implements IPublisher {
    private List<ISubscriber> listSubscriber=new ArrayList<>();

    @Override
    public void addSubscriber(ISubscriber s) {
        if(!listSubscriber.contains(s)){
            listSubscriber.add(s);
        }
    }

    @Override
    public void removeSubscriber(ISubscriber s) {
        if(listSubscriber.contains(s)){
            listSubscriber.remove(s);
        }
    }

    @Override
    public void notifySubscriber(Object o) {
        for(ISubscriber subscriber: listSubscriber){
            subscriber.update(o);
        }

    }

    public void createMessage(String content, MessageType type){
        Message message = new Message(content, type);
        notifySubscriber(message);
    }
}
