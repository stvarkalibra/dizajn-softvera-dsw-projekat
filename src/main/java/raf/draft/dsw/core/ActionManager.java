package raf.draft.dsw.core;

import lombok.Getter;
import raf.draft.dsw.controller.ExitAction;


public class ActionManager {
    private ExitAction exitAction = new ExitAction();

    public ExitAction getExitAction(){
        return  this.exitAction;
    }
}
