package raf.draft.dsw.core;

import raf.draft.dsw.controller.actions.*;
import raf.draft.dsw.gui.swing.MainFrame;
import raf.draft.dsw.serializer.Gson;


public class ActionManager {
    private Gson gson=new Gson();
    private ExitAction exitAction = new ExitAction();
    private AboutUsAction aboutUsAction = new AboutUsAction();
    private NewNodeAction newNodeAction=new NewNodeAction();
    private RemoveNodeAction removeNodeAction=new RemoveNodeAction();
    private ChangeInfoAction changeInfoAction=new ChangeInfoAction();
    private ProjectSaveAsAction projectSaveAsAction=new ProjectSaveAsAction(gson);
    private ProjectSaveAction projectSaveAction=new ProjectSaveAction(gson);
    private OpenProjectAction openProject=new OpenProjectAction(gson);
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

    public ProjectSaveAsAction getProjectSaveAsAction() {
        return projectSaveAsAction;
    }

    public ProjectSaveAction getProjectSaveAction() {
        return projectSaveAction;
    }

    public OpenProjectAction getOpenProject() {
        return openProject;
    }
}
