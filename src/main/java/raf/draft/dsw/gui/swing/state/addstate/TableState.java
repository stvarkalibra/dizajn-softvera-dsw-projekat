package raf.draft.dsw.gui.swing.state.addstate;

import raf.draft.dsw.elements.RoomElement;
import raf.draft.dsw.elements.Table;
import raf.draft.dsw.elements.contoller.ElementsController;
import raf.draft.dsw.gui.swing.ElementDimensionFrame;
import raf.draft.dsw.gui.swing.state.State;
import raf.draft.dsw.jtabbedpane.view.RoomView;
import raf.draft.dsw.model.nodes.DraftNode;

import java.awt.*;

public class TableState implements State {
    @Override
    public void myMouseClicked(RoomView roomView, Point location) {
        boolean sameLocation=false;

        for(RoomElement roomElement:roomView.getRoom().getRoomElements()){
            if(roomElement.getLocation().equals(roomView.getRoom().getPendingElement().getLocation())){
                sameLocation=true;
            }
        }
        if(!sameLocation){
            roomView.getRoom().getRoomElements().add(roomView.getRoom().getPendingElement());
            roomView.getRoom().getChildrenList().add(roomView.getRoom().getPendingElement());
            System.out.println("ELEMENT DODAT: " + roomView.getRoom().getPendingElement().toString());
            System.out.println("SVI ELEMENTI: " + roomView.getRoom().getRoomElements().size());
            roomView.getRoom().notifySubscriber(roomView.getRoom().getPendingElement());
        }

    }

    @Override
    public void myMouseScroll() {

    }

    @Override
    public void myMouseReleased() {

    }

    @Override
    public void myMouseDragged() {

    }
}
