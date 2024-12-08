package raf.draft.dsw.gui.swing.state.addstate;

import raf.draft.dsw.elements.BathTub;
import raf.draft.dsw.elements.Boiler;
import raf.draft.dsw.elements.ToiletSeat;
import raf.draft.dsw.gui.swing.state.State;

import java.util.Stack;

public class AddStateManager{
    State currentState;
    private BathTubState bathTubState=new BathTubState();
    private BedState bedState=new BedState();
    private BoilerState boilerState=new BoilerState();
    private ClosetState closetState=new ClosetState();
    private DoorState doorState=new DoorState();
    private SinkState sinkState=new SinkState();
    private TableState tableState=new TableState();
    private ToiletState toiletState=new ToiletState();
    private WashingMachineState washingMachineState=new WashingMachineState();
    public void setBathTubState(){
        currentState=bathTubState;
    }public void setBedState(){
        currentState=bedState;
    }public void setBoilerState(){
        currentState=boilerState;
    }public void setClosetState(){
        currentState=closetState;
    }public void setDoorState(){
        currentState=doorState;
    }public void setSinkState(){
        currentState=sinkState;
    }public void setTableState() {
        currentState=tableState;
    }public void setToiletState(){
        currentState=toiletState;
    }public void setWashingMachineState(){
        currentState=washingMachineState;
    }public State getCurrentState() {
        return currentState;
    }
}
