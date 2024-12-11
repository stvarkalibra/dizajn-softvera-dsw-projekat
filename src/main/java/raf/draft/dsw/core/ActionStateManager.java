package raf.draft.dsw.core;

import raf.draft.dsw.controller.actions.stateactions.*;

public class ActionStateManager {
    private EditRoomAction editRoomAction = new EditRoomAction();
    private AddAction addAction = new AddAction();
    private SelectAction selectAction = new SelectAction();
    private ResizeAction resizeAction = new ResizeAction();
    private RotateLeftAction rotateLeftAction = new RotateLeftAction();
    private RotateRightAction rotateRightAction = new RotateRightAction();
    private CopyAndPasteAction copyAndPasteAction = new CopyAndPasteAction();
    private MoveAction moveAction = new MoveAction();
    private ZoomAction zoomAction = new ZoomAction();
    private EditAction editAction = new EditAction();
    private DeleteAction deleteAction = new DeleteAction();

    public AddAction getAddAction() {
        return addAction;
    }

    public CopyAndPasteAction getCopyAndPasteAction() {
        return copyAndPasteAction;
    }

    public DeleteAction getDeleteAction() {
        return deleteAction;
    }

    public EditAction getEditAction() {
        return editAction;
    }

    public EditRoomAction getEditRoomAction() {
        return editRoomAction;
    }

    public MoveAction getMoveAction() {
        return moveAction;
    }

    public ResizeAction getResizeAction() {
        return resizeAction;
    }

    public RotateLeftAction getRotateLeftAction() {
        return rotateLeftAction;
    }

    public RotateRightAction getRotateRightAction() {
        return rotateRightAction;
    }

    public SelectAction getSelectAction() {
        return selectAction;
    }

    public ZoomAction getZoomAction() {
        return zoomAction;
    }
}
