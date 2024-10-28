package raf.draft.dsw.controller.messagegenerator;

import raf.draft.dsw.controller.observer.ISubscriber;
import raf.draft.dsw.model.message.Message;

public interface ILogger extends ISubscriber {
    @Override
    void update(Object o);
}
