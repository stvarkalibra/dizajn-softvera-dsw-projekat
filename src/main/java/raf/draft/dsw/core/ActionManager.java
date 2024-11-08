package raf.draft.dsw.core;

import raf.draft.dsw.controller.actions.*;


public class ActionManager {
    private ExitAction exitAction = new ExitAction();
    private AboutUsAction aboutUsAction = new AboutUsAction();
    private NewNodeAction newNodeAction=new NewNodeAction();
    private RemoveNodeAction removeNodeAction=new RemoveNodeAction();
    private ChangeInfoAction changeInfoAction=new ChangeInfoAction();
    public NewNodeAction getNewNodeAction(){
        return this.newNodeAction;
    }
    public RemoveNodeAction getRemoveNodeAction(){
        return this.removeNodeAction;
    }
    public ExitAction getExitAction(){
        return  this.exitAction;
    }
    public AboutUsAction getAboutUsAction() {
        return aboutUsAction;
    }
    public ChangeInfoAction getChangeInfoAction() {return changeInfoAction;}
}
