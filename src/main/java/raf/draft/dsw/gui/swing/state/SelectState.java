package raf.draft.dsw.gui.swing.state;

import raf.draft.dsw.elements.RoomElement;
import raf.draft.dsw.jtabbedpane.view.RoomView;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelEvent;

public class SelectState implements State {
    private Point dragStartPoint;
    private Rectangle selectionRectangle;

    @Override
    public void myMouseClicked(RoomView roomView, Point location) {
        roomView.getSelectedElements().clear();
        for (RoomElement roomElement : roomView.getRoom().getRoomElements()) {
            if (roomElement.getBounds().contains(location)) {
                roomView.getSelectedElements().add(roomElement);
                System.out.println(location);
                System.out.println(roomView.getSelectedElements().get(0).getBounds());
                System.out.println(roomView.getSelectedElements());
                break;
            }
        }

    }

    @Override
    public void myMouseScrolled(RoomView roomView, MouseWheelEvent event) {
    }

    @Override
    public void myMouseReleased(RoomView roomView) {
        dragStartPoint = null;
        selectionRectangle = null;
    }

    @Override
    public void myMouseDragged(RoomView roomView, MouseEvent e) {
        Point dragEndPoint = e.getPoint();
        if (dragStartPoint != null) {
            int x = Math.min(dragStartPoint.x, dragEndPoint.x);
            int y = Math.min(dragStartPoint.y, dragEndPoint.y);
            int width = Math.abs(dragStartPoint.x - dragEndPoint.x);
            int height = Math.abs(dragStartPoint.y - dragEndPoint.y);
            selectionRectangle = new Rectangle(x, y, width, height);
            roomView.getSelectedElements().clear();

            for (RoomElement element : roomView.getRoom().getRoomElements()) {
                if (element.getBounds() != null && selectionRectangle.contains(element.getBounds())) {
                    roomView.getSelectedElements().add(element);
                }
            }
        }
        System.out.println(roomView.getSelectedElements());
    }

    public void myMousePressed(MouseEvent e) {
        dragStartPoint = e.getPoint();
    }

    @Override
    public void update(Object o) {
    }
}
