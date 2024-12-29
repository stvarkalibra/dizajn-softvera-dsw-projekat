package raf.draft.dsw.commandPattern;

import raf.draft.dsw.elements.RoomElement;
import raf.draft.dsw.jtabbedpane.view.RoomView;

import java.util.ArrayList;
import java.util.List;

public class RotateCommand extends AbstractCommand{
    int newRotation;
    int oldRotation;
    private List<RoomElement> roomElements;

    public RotateCommand(RoomView roomView, int newRotation, int oldRotation, List<RoomElement> roomElements) {
        super(roomView);
        this.newRotation = newRotation;
        this.oldRotation = oldRotation;
        this.roomElements = roomElements;
    }

    @Override
    public void doCommand() {
        for(RoomElement roomElement:roomElements){
            if(newRotation!=roomElement.getRotation()){
                roomElement.setRotation(newRotation);
            }
        }
    }
    @Override
    public void undoCommand() {
        for(RoomElement roomElement:roomElements){
            roomElement.setRotation(oldRotation);
        }
    }
}
