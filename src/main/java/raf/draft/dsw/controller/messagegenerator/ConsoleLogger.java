package raf.draft.dsw.controller.messagegenerator;

import raf.draft.dsw.model.message.Message;

public class ConsoleLogger implements ILogger {
    @Override
    public void update(Object o) {
        if (o instanceof Message){
            System.out.println(o);
        }
    }
}
