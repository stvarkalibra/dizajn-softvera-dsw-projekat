package raf.draft.dsw.core;

import raf.draft.dsw.controller.actions.AboutUsAction;
import raf.draft.dsw.controller.actions.ExitAction;
import raf.draft.dsw.controller.actions.NewNodeAction;


public class ActionManager {
    private ExitAction exitAction = new ExitAction();
    private AboutUsAction aboutUsAction = new AboutUsAction();
    private NewNodeAction newNodeAction=new NewNodeAction();
    public NewNodeAction getNewNodeAction(){
        return this.newNodeAction;
    }

    public ExitAction getExitAction(){
        return  this.exitAction;
    }

    public AboutUsAction getAboutUsAction() {
        return aboutUsAction;
    }

}
