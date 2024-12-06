package raf.draft.dsw.gui.swing.state;

import raf.draft.dsw.elements.RoomElement;
import raf.draft.dsw.elements.contoller.ElementsController;
import raf.draft.dsw.gui.swing.ElementDimensionFrame;
import raf.draft.dsw.jtabbedpane.view.RoomView;
import raf.draft.dsw.model.nodes.DraftNode;

import java.awt.*;

public class AddState implements State{
    @Override
    public void myMouseClicked(RoomView roomView) {

        if(roomView.getRoom().getPendingElement()!=null) {
            boolean sameLocation = false;
            ElementDimensionFrame elementDimensionFrame = new ElementDimensionFrame();

            ElementsController elementsController = new ElementsController();
            elementsController.setLenght(elementDimensionFrame.getLenght());
            elementsController.setWidht(elementDimensionFrame.getWidth());
            elementsController.dimensions(roomView.getRoom().getPendingElement());

            for (DraftNode draftNode : roomView.getRoom().getChildrenList()) {
                if (draftNode instanceof RoomElement) {
                    if (((RoomElement) draftNode).getLocation() == roomView.getMouseLocation()) {
                        sameLocation = true;
                    }
                }
            }
            if (!sameLocation) {
                elementsController.setPoint(roomView.getMouseLocation());
                elementsController.location(roomView.getRoom().getPendingElement());
                roomView.getRoom().getChildrenList().add(roomView.getRoom().getPendingElement());
                roomView.revalidate();
                roomView.repaint();
                System.out.println(roomView.getRoom().getPendingElement().toString());
                roomView.getRoom().setPendingElement(null);
            }
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
