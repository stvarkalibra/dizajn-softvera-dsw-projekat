package raf.draft.dsw.gui.swing;

import raf.draft.dsw.controller.actions.stateactions.*;
import raf.draft.dsw.core.ActionStateManager;
import raf.draft.dsw.gui.swing.state.StateManager;
import raf.draft.dsw.gui.swing.state.addstate.AddStateManager;

import javax.swing.*;

public class StateToolBar extends JToolBar {
    public StateToolBar(ActionStateManager actionStateManager){
        super(HORIZONTAL);
        setFloatable(false);
        EditRoomAction editRoomAction = actionStateManager.getEditRoomAction();
        AddAction addAction = actionStateManager.getAddAction();
        SelectAction selectAction = actionStateManager.getSelectAction();
        ResizeAction resizeAction = actionStateManager.getResizeAction();
        RotateLeftAction rotateLeftAction = actionStateManager.getRotateLeftAction();
        RotateRightAction rotateRightAction = actionStateManager.getRotateRightAction();
        CopyAndPasteAction copyAndPasteAction = actionStateManager.getCopyAndPasteAction();
        MoveAction moveAction = actionStateManager.getMoveAction();
        ZoomAction zoomAction = actionStateManager.getZoomAction();
        EditAction editAction = actionStateManager.getEditAction();
        DeleteAction deleteAction = actionStateManager.getDeleteAction();
        RedoAction redoAction=actionStateManager.getRedoAction();
        UndoAction undoAction=actionStateManager.getUndoAction();

        add(editRoomAction);
        add(addAction);
        add(selectAction);
        add(resizeAction);
        add(rotateLeftAction);
        add(rotateRightAction);
        add(copyAndPasteAction);
        add(moveAction);
        add(zoomAction);
        add(editAction);
        add(deleteAction);
        add(undoAction);
        add(redoAction);

    }
}
