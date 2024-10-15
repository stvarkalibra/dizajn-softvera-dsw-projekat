package raf.draft.dsw.core;

import raf.draft.dsw.controller.AboutUsAction;
import raf.draft.dsw.controller.ExitAction;


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
