package raf.draft.dsw.controller.observer;

import raf.draft.dsw.model.message.Message;

public interface IPublisher extends ISubscriber {
    public void addSubscriber(ISubscriber s);
    public void removeSubscriber(ISubscriber s);
    public void notifySubscriber(Message message);
}
