package raf.draft.dsw.core;

import raf.draft.dsw.controller.actions.*;
import raf.draft.dsw.serializer.JacksonSerializer;


public class ActionManager {
    private JacksonSerializer jacksonSerializer =new JacksonSerializer();
    private ExitAction exitAction = new ExitAction();
    private AboutUsAction aboutUsAction = new AboutUsAction();
    private NewNodeAction newNodeAction=new NewNodeAction();
    private RemoveNodeAction removeNodeAction=new RemoveNodeAction();
    private ChangeInfoAction changeInfoAction=new ChangeInfoAction();
    private ProjectSaveAsAction projectSaveAsAction=new ProjectSaveAsAction(jacksonSerializer);
    private ProjectSaveAction projectSaveAction=new ProjectSaveAction(jacksonSerializer);
    private OpenProjectAction openProject=new OpenProjectAction(jacksonSerializer);
    private OpenTemplate openTemplate=new OpenTemplate();
    private SaveTemplate saveTemplate=new SaveTemplate();
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

    public OpenTemplate getOpenTemplate() {
        return openTemplate;
    }

    public SaveTemplate getSaveTemplate() {
        return saveTemplate;
    }
}
