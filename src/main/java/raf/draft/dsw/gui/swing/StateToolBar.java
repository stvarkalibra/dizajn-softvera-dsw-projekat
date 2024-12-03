package raf.draft.dsw.gui.swing;

import raf.draft.dsw.controller.actions.*;
import raf.draft.dsw.core.ActionManager;
import raf.draft.dsw.gui.swing.state.EditRoomState;
import raf.draft.dsw.gui.swing.state.StateManager;

import javax.swing.*;

public class StateToolBar extends JToolBar {
    public StateToolBar(StateManager stateManager){
        super(HORIZONTAL);
        setFloatable(false);
        EditStateAction editStateAction=new EditStateAction();
        add(editStateAction);
    }
}
