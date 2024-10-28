package raf.draft.dsw.controller.observer;

import raf.draft.dsw.model.message.Message;

public interface ISubscriber {
    public void update(Object o);
}
