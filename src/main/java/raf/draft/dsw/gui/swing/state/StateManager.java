package raf.draft.dsw.gui.swing.state;

import raf.draft.dsw.gui.swing.state.addstate.*;

public class StateManager {
    private EditRoomState editRoomState;
    private AddState addState;
    private SelectState selectState;
    private CopyAndPasteState copyAndPasteState;
    private RotateState rotateState;
    private ResizeState resizeState;
    private MoveState moveState;
    private EditState editState;
    private ZoomState zoomState;
    private DeleteState deleteState;
    private State currentState=null;


    public StateManager() {
        this.editRoomState = new EditRoomState();
        this.addState = new AddState();
        this.selectState = new SelectState();
        this.copyAndPasteState = new CopyAndPasteState();
        this.rotateState = new RotateState();
        this.resizeState = new ResizeState();
        this.moveState = new MoveState();
        this.editState = new EditState();
        this.zoomState = new ZoomState();
        this.deleteState = new DeleteState();
    }
    public void setEditRoomState(){
        this.currentState=editRoomState;
    }public void setAddState(){
        this.currentState=addState;
    }public void setSelectState(){
        this.currentState=selectState;
    }public void setRotateState(){
        this.currentState=rotateState;
    }public void setResizeState(){
        this.currentState=resizeState;
    }public void setMoveState(){
        this.currentState=moveState;
    }public void setCopyAndPasteState(){
        this.currentState=copyAndPasteState;
    }public void setZoomState(){
        this.currentState=zoomState;
    }public void setDeleteState(){
        this.currentState=deleteState;
    }public void setEditState(){
        this.currentState=editState;
    }

    public State getCurrentState() {
        return currentState;
    }
}
