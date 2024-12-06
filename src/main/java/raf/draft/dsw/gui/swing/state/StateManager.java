package raf.draft.dsw.gui.swing.state;

public class StateManager {
    private EditRoomState editRoomState;
    private State currentState=null;

    public StateManager() {
        this.editRoomState = new EditRoomState();
    }
    public void setEditRoomState(){
        this.currentState=editRoomState;
    }

    public State getCurrentState() {
        return currentState;
    }
}
