package raf.draft.dsw.gui.swing.state;

import raf.draft.dsw.elements.RoomElement;
import raf.draft.dsw.gui.swing.painters.MyPainter;
import raf.draft.dsw.jtabbedpane.view.RoomView;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class DeleteState implements State{
    @Override
    public void myMouseClicked(RoomView roomView, Point location) {
        Iterator<RoomElement> roomElementIterator = roomView.getRoom().getRoomElements().iterator();
        while (roomElementIterator.hasNext()) {
            RoomElement roomElement = roomElementIterator.next();
            if (roomElement.getBounds().contains(location)) {
                roomElementIterator.remove();
                roomView.getRoom().getChildrenList().remove(roomElement);
                Iterator<MyPainter> painterIterator = roomView.getPainterList().iterator();
                while (painterIterator.hasNext()) {
                    MyPainter painter = painterIterator.next();
                    if (painter.getRoomElement().equals(roomElement)) {
                        painterIterator.remove();
                        break;
                    }
                }
                //roomView.deleteElementFromTree(roomElement);
            }
        }
        roomView.getjPanelRoom().revalidate();
        roomView.getjPanelRoom().repaint();
    }

    @Override
    public void myMouseScrolled() {

    }

    @Override
    public void myMouseReleased(RoomView roomView) {

    }

    @Override
    public void myMouseDragged(RoomView roomView, MouseEvent mouseEvent) {

    }
    @Override
    public void update(Object o) {

    }
}
