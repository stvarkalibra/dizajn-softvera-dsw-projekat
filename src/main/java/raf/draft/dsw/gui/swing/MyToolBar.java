package raf.draft.dsw.gui.swing;

import raf.draft.dsw.controller.ExitAction;
import raf.draft.dsw.core.ActionManager;

import javax.swing.*;

public class MyToolBar extends JToolBar {
    public MyToolBar(ActionManager actionManager){
        super(HORIZONTAL);
        setFloatable(false);
        ExitAction ea = actionManager.getExitAction();
        add(ea);
    }
}
