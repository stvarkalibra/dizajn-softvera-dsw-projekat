package raf.draft.dsw.gui.swing.state;

import raf.draft.dsw.commandPattern.CommandManager;
import raf.draft.dsw.commandPattern.MoveCommand;
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
    @Override
    public void myMouseClicked(RoomView roomView, Point location) {
    }

    @Override
    public void myMouseScrolled(RoomView roomView, MouseWheelEvent event) {
    }

    @Override
    public void myMouseReleased(RoomView roomView) {

        List<RoomElement> selectedElements = roomView.getSelectedElements();

        if (selectedElements.isEmpty()) {
            if (initialMousePosition == null || initialElementPositions == null) {
                return;
            }

            List<RoomElement> allElements = roomView.getRoom().getRoomElements();
            for (int i = 0; i < allElements.size(); i++) {
                RoomElement element = allElements.get(i);
                Point newLocation = element.getLocation();
                element.setBounds(new Rectangle(newLocation, new Dimension(
                        element.getDimensionWidth() * (int) roomView.getK(),
                        element.getDimensionLength() * (int) roomView.getK()
                )));
                element.addSubscriber(roomView);
                element.notifySubscriber(element);
            }
        }
        List<Point> newLocations=new ArrayList<>();
        boolean hasIntersection = false;
        for (RoomElement element : selectedElements) {
            newLocations.add(element.getLocation());
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
                selectedElements.get(i).addSubscriber(roomView);
                selectedElements.get(i).notifySubscriber(selectedElements.get(i));
            }
        }else if(!hasIntersection){
            MoveCommand moveCommand=new MoveCommand(roomView,initialElementPositions, newLocations);
            roomView.getCommandManager().addCommand(moveCommand);
        }


        if (selectedElements.isEmpty()) {
            initialMousePosition = null;
            initialElementPositions = null;
        }
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
            // Trenutna pozicija miša
            Point currentMousePosition = mouseEvent.getPoint();

            // Inicijalizacija početnih pozicija i miša ako je potrebno
            if (initialMousePosition == null || initialElementPositions == null) {
                initialMousePosition = currentMousePosition;
                initialElementPositions = new ArrayList<>();

                // Kreiranje početnih pozicija za trenutne selektovane elemente
                for (RoomElement element : selectedElements) {
                    initialElementPositions.add(new Point(element.getLocation()));
                }
            }

            // Ako veličine liste nisu usklađene, uskladi ih
            if (initialElementPositions.size() != selectedElements.size()) {
                // Sinhronizacija početnih pozicija s trenutnim selektovanim elementima
                initialElementPositions.clear();
                for (RoomElement element : selectedElements) {
                    initialElementPositions.add(new Point(element.getLocation()));
                }
            }

            // Izračunaj pomeraj miša (delta)
            int deltaX = currentMousePosition.x - initialMousePosition.x;
            int deltaY = currentMousePosition.y - initialMousePosition.y;

            // Pomeranje svih selektovanih elemenata
            for (int i = 0; i < selectedElements.size(); i++) {
                RoomElement element = selectedElements.get(i);
                Point originalLocation = initialElementPositions.get(i);

                // Nova pozicija elementa bazirana na delta pomeraju
                Point newLocation = new Point(
                        originalLocation.x + deltaX,
                        originalLocation.y + deltaY
                );

                // Opcionalno: Snap na ivice (ako je potrebno)
                newLocation = snapToEdge(newLocation, roomView, element);

                // Postavi novu lokaciju elementa
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