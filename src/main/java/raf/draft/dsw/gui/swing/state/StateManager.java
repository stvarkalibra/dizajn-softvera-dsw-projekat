package raf.draft.dsw.gui.swing.state;

public class StateManager {
    private EditRoomState editRoomState=new EditRoomState();
    private State currentState;

    public EditRoomState getEditRoomState() {
        return editRoomState;
    }

    public void setEditRoomState(){
        this.currentState=editRoomState;
    }

    public State getCurrentState() {
        return currentState;
    }

    public void setCurrentState(State currentState) {
        this.currentState = currentState;
    }
}
