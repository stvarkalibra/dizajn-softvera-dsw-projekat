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
        if (selectedElements.isEmpty()) return;

        Point currentMousePosition = mouseEvent.getPoint();

        // Inicijalizacija za prvo povlačenje
        if (initialMousePosition == null) {
            initialMousePosition = currentMousePosition;
            initialElementPositions = new ArrayList<>();
            for (RoomElement element : selectedElements) {
                initialElementPositions.add(new Point(element.getLocation())); // Gornji levi ugao
            }
        }

        // Računanje pomeraja
        int deltaX = currentMousePosition.x - initialMousePosition.x;
        int deltaY = currentMousePosition.y - initialMousePosition.y;

        // Pomeranje elemenata
        for (RoomElement element : selectedElements) {
            Point originalLocation = initialElementPositions.get(selectedElements.indexOf(element));
            Point newLocation = new Point(
                    originalLocation.x + deltaX,
                    originalLocation.y + deltaY
            );
            element.setLocation(newLocation); // This will update bounds as well
        }

        roomView.getjPanelRoom().repaint();
    }


    @Override
    public void update(Object o) {
        // Not needed for this state
    }
}