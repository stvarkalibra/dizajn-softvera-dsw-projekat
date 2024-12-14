package raf.draft.dsw.gui.swing.state;

import raf.draft.dsw.elements.RoomElement;
import raf.draft.dsw.jtabbedpane.view.RoomView;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelEvent;
import java.util.ArrayList;
import java.util.List;

public class MoveState implements State {

    private Point initialMousePosition;
    private List<Point> initialElementPositions;
    private Point initialRoomPosition;

    @Override
    public void myMouseClicked(RoomView roomView, Point location) {
        // Not needed for movement
    }

    @Override
    public void myMouseScrolled(RoomView roomView, MouseWheelEvent event) {
        // Not needed for movement
    }

    @Override
    public void myMouseReleased(RoomView roomView) {

        List<RoomElement> selectedElements = roomView.getSelectedElements();

        if (selectedElements.isEmpty()) {
            return;
        }

        boolean hasIntersection = false;
        for (RoomElement element : selectedElements) {
            for (RoomElement other : roomView.getRoom().getRoomElements()) {
                if (other != element && other.getBounds().intersects(element.getBounds())) {
                    hasIntersection = true;
                    break;
                }
            }
            if (hasIntersection) break;
        }

        if (hasIntersection) {
            for (int i = 0; i < selectedElements.size(); i++) {
                selectedElements.get(i).setLocation(initialElementPositions.get(i));
                System.out.println(selectedElements.get(i));
            }
        }

        if (selectedElements.isEmpty()) {
            initialMousePosition = null;
            initialElementPositions = null;
        }

        roomView.getjPanelRoom().repaint();
    }

    @Override
    public void myMouseDragged(RoomView roomView, MouseEvent mouseEvent) {
        List<RoomElement> selectedElements = roomView.getSelectedElements();

        if (selectedElements.isEmpty()) {
            Point currentMousePosition = mouseEvent.getPoint();

            if (initialMousePosition == null) {
                initialMousePosition = currentMousePosition;
                initialElementPositions = new ArrayList<>();
                for (RoomElement element : roomView.getRoom().getRoomElements()) {
                    initialElementPositions.add(new Point(element.getLocation()));
                }
            }
            int deltaX = currentMousePosition.x - initialMousePosition.x;
            int deltaY = currentMousePosition.y - initialMousePosition.y;

            for (RoomElement element : roomView.getRoom().getRoomElements()) {
                Point originalLocation = initialElementPositions.get(roomView.getRoom().getChildrenList().indexOf(element));
                Point newLocation = new Point(
                        originalLocation.x + deltaX,
                        originalLocation.y + deltaY
                );
                element.setLocation(newLocation);
            }
            roomView.getjPanelRoom().revalidate();
            roomView.getjPanelRoom().repaint();
        } else {
            Point currentMousePosition = mouseEvent.getPoint();

            if (initialMousePosition == null) {
                initialMousePosition = currentMousePosition;
                initialElementPositions = new ArrayList<>();
                for (RoomElement element : selectedElements) {
                    initialElementPositions.add(new Point(element.getLocation()));
                }
            }
            int deltaX = currentMousePosition.x - initialMousePosition.x;
            int deltaY = currentMousePosition.y - initialMousePosition.y;
            for (RoomElement element : selectedElements) {
                Point originalLocation = initialElementPositions.get(selectedElements.indexOf(element));
                Point newLocation = new Point(
                        originalLocation.x + deltaX,
                        originalLocation.y + deltaY
                );
                newLocation = snapToEdge(newLocation, roomView, element);
                element.setLocation(newLocation);
            }
            roomView.getjPanelRoom().repaint();
        }
    }

    private Point snapToEdge(Point location, RoomView roomView, RoomElement element) {
        int roomWidth = roomView.getjPanelRoom().getWidth();
        int roomHeight = roomView.getjPanelRoom().getHeight();

        int elementWidth = element.getDimensionWidth()*(int)roomView.getK();
        int elementHeight = element.getDimensionLength()*(int)roomView.getK();

        int leftEdge = location.x;
        int rightEdge = location.x + elementWidth;
        int topEdge = location.y;
        int bottomEdge = location.y + elementHeight;

        if (leftEdge < 10) {
            location.x = 0;
        } else if (rightEdge > roomWidth - 10) {
            location.x = roomWidth - elementWidth;
        }if (topEdge < 10) {
            location.y = 0;
        } else if (bottomEdge > roomHeight - 10) {
            location.y = roomHeight - elementHeight;
        }

        return location;
    }



    @Override
    public void update(Object o) {
    }
}