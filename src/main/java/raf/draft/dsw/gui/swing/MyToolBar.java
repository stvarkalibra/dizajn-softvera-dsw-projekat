package raf.draft.dsw.gui.swing;

import raf.draft.dsw.controller.actions.*;
import raf.draft.dsw.core.ActionManager;

import javax.swing.*;

public class MyToolBar extends JToolBar {
    public MyToolBar(ActionManager actionManager){
        super(HORIZONTAL);
        setFloatable(false);
        ExitAction exitAction = actionManager.getExitAction();
        AboutUsAction aboutUsAction = actionManager.getAboutUsAction();
        NewNodeAction newNodeAction = actionManager.getNewNodeAction();
        RemoveNodeAction removeNodeAction = actionManager.getRemoveNodeAction();
        ChangeInfoAction changeInfoAction =actionManager.getChangeInfoAction();
        ProjectSaveAction projectSaveAction=actionManager.getProjectSaveAction();
        ProjectSaveAsAction projectSaveAsAction=actionManager.getProjectSaveAsAction();
        OpenProjectAction openProjectAction=actionManager.getOpenProject();
        add(exitAction);
        add(newNodeAction);
        add(changeInfoAction);
        add(removeNodeAction);
        add(aboutUsAction);
        add(projectSaveAction);
        add(projectSaveAsAction);
        add(openProjectAction);
    }
}
