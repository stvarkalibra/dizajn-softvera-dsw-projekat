package raf.draft.dsw.gui.swing;

import raf.draft.dsw.controller.actions.stateactions.*;
import raf.draft.dsw.gui.swing.state.EditState;
import raf.draft.dsw.gui.swing.state.StateManager;

import javax.swing.*;

public class StateToolBar extends JToolBar {
    public StateToolBar(StateManager stateManager){
        super(HORIZONTAL);
        setFloatable(false);
        EditRoomAction editRoomAction=new EditRoomAction();
        AddAction addAction=new AddAction();
        SelectAction selectAction=new SelectAction();
        ResizeAction resizeAction=new ResizeAction();
        RotateAction rotateAction=new RotateAction();
        CopyAndPasteAction copyAndPasteAction=new CopyAndPasteAction();
        MoveAction moveAction=new MoveAction();
        ZoomAction zoomAction=new ZoomAction();
        EditAction editAction=new EditAction();
        DeleteAction deleteAction=new DeleteAction();
        add(editRoomAction);
        add(addAction);
        add(selectAction);
        add(resizeAction);
        add(rotateAction);
        add(copyAndPasteAction);
        add(moveAction);
        add(zoomAction);
        add(editAction);
        add(deleteAction);
    }
}
