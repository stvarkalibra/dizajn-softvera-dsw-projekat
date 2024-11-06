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
        add(exitAction);
        add(aboutUsAction);
        add(newNodeAction);
        add(removeNodeAction);
    }
}
