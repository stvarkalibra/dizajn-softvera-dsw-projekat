package raf.draft.dsw.core;

import raf.draft.dsw.controller.actions.AboutUsAction;
import raf.draft.dsw.controller.actions.ExitAction;


public class ActionManager {
    private ExitAction exitAction = new ExitAction();
    private AboutUsAction aboutUsAction = new AboutUsAction();

    public ExitAction getExitAction(){
        return  this.exitAction;
    }

    public AboutUsAction getAboutUsAction() {
        return aboutUsAction;
    }
}
