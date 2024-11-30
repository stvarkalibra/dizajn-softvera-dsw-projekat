package raf.draft.dsw.controller.messagegenerator;

import raf.draft.dsw.controller.observer.IPublisher;
import raf.draft.dsw.controller.observer.ISubscriber;
import raf.draft.dsw.model.message.Message;
import raf.draft.dsw.model.message.MessageType;

import java.util.ArrayList;
import java.util.List;

public class MessageGenerator implements IPublisher {
    private List<ISubscriber> subscriberList =new ArrayList<>();

    public MessageGenerator() {
        ConsoleLogger consoleLogger=new ConsoleLogger();
        FileLogger fileLogger=new FileLogger("files/log.txt");
        addSubscriber(consoleLogger);
        addSubscriber(fileLogger);
    }

    @Override
    public void addSubscriber(ISubscriber s) {
        if(!subscriberList.contains(s)){
            subscriberList.add(s);
        }
    }

    @Override
    public void removeSubscriber(ISubscriber s) {
        if(subscriberList.contains(s)){
            subscriberList.remove(s);
        }
    }

    @Override
    public void notifySubscriber(Object o) {
        for(ISubscriber subscriber: subscriberList){
            subscriber.update(o);
        }

    }

    public void createMessage(String content, MessageType type){
        Message message = new Message(content, type);
        notifySubscriber(message);

    }
}
