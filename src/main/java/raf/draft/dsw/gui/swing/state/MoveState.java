package raf.draft.dsw.gui.swing.state;

import raf.draft.dsw.elements.RoomElement;
import raf.draft.dsw.jtabbedpane.view.RoomView;

import java.awt.*;
import java.awt.event.MouseEvent;
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
    public void myMouseScrolled() {
        // Not needed for movement
    }

    @Override
    public void myMouseReleased(RoomView roomView) {

        List<RoomElement> selectedElements = roomView.getSelectedElements();

        if (selectedElements.isEmpty()) return;
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
        initialMousePosition = null;
        initialElementPositions = null;

        roomView.getjPanelRoom().repaint();
    }

    @Override
    public void myMouseDragged(RoomView roomView, MouseEvent mouseEvent) {
        List<RoomElement> selectedElements = roomView.getSelectedElements();

        // Ako nema selektovanih elemenata, pomeramo samo jPanelRoom
        if (selectedElements.isEmpty()) {
            Point currentMousePosition = mouseEvent.getPoint();

            // Ako je ovo prvi put da pomeramo jPanelRoom, čuvamo početnu poziciju jPanelRoom
            if (initialRoomPosition == null) {
                initialMousePosition = currentMousePosition;  // Postavljamo početnu poziciju miša
                initialRoomPosition = roomView.getjPanelRoom().getLocation();  // Čuvamo početnu poziciju jPanelRoom
            }

            // Računamo pomeranje sobe
            int deltaX = currentMousePosition.x - initialMousePosition.x;
            int deltaY = currentMousePosition.y - initialMousePosition.y;

            // Nova pozicija jPanelRoom
            Point newRoomPosition = new Point(
                    initialRoomPosition.x + deltaX,
                    initialRoomPosition.y + deltaY
            );

            // Pomeramo samo jPanelRoom unutar JScrollPane
            roomView.getjPanelRoom().setLocation(newRoomPosition);
        } else {
            // Postojeći kod za pomeranje selektovanih elemenata
            Point currentMousePosition = mouseEvent.getPoint();

            // Inicijalizuj samo prvi put kad se pomera
            if (initialMousePosition == null) {
                initialMousePosition = currentMousePosition;
                initialElementPositions = new ArrayList<>();
                for (RoomElement element : selectedElements) {
                    initialElementPositions.add(new Point(element.getLocation()));
                }
            }

            // Računanje pomeranja elemenata
            int deltaX = currentMousePosition.x - initialMousePosition.x;
            int deltaY = currentMousePosition.y - initialMousePosition.y;

            // Pomeri svaki selektovani element
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
        // Not needed for this state
    }
}