package raf.draft.dsw.gui.swing;

import raf.draft.dsw.controller.actions.AboutUsAction;
import raf.draft.dsw.controller.actions.ExitAction;
import raf.draft.dsw.controller.actions.NewNodeAction;
import raf.draft.dsw.controller.actions.RemoveNodeAction;
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
