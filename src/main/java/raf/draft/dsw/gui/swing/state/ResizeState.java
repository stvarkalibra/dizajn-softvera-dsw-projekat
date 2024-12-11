package raf.draft.dsw.gui.swing.state;

import raf.draft.dsw.elements.RoomElement;
import raf.draft.dsw.jtabbedpane.view.RoomView;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import java.util.List;

public class ResizeState implements State {
    private Point dragStartPoint;
    private List<RoomElement> elementsBeingResized = new ArrayList<>();
    private List<Rectangle2D> initialBoundsList = new ArrayList<>();
    private RoomElement elementBeingResized;

    @Override
    public void myMouseClicked(RoomView roomView, Point location) {
        // No specific behavior for click in this state.
    }

    @Override
    public void myMouseScrolled() {
        // No scrolling behavior needed for resizing.
    }

    @Override
    public void myMouseReleased() {
        // Clear references when the drag ends.
        dragStartPoint = null;
        elementsBeingResized.clear();
        initialBoundsList.clear();
        elementBeingResized = null;
    }

    @Override
    public void myMouseDragged(RoomView roomView, MouseEvent e) {
        Point dragEndPoint = e.getPoint();
        if (!elementsBeingResized.isEmpty()) {
            double scaleX = (dragEndPoint.getX() - dragStartPoint.getX()) / dragStartPoint.getX();
            double scaleY = (dragEndPoint.getY() - dragStartPoint.getY()) / dragStartPoint.getY();

            for (int i = 0; i < elementsBeingResized.size(); i++) {
                RoomElement element = elementsBeingResized.get(i);
                Rectangle2D initialBounds = initialBoundsList.get(i);

                double newWidth = initialBounds.getWidth() * (1 + scaleX);
                double newHeight = initialBounds.getHeight() * (1 + scaleY);

                element.setBounds(new Rectangle(
                        (int) initialBounds.getX(),
                        (int) initialBounds.getY(),
                        (int) newWidth,
                        (int) newHeight
                ));

                element.setDimensionLenght((int) (newHeight / roomView.getK()));
                element.setDimensionWidth((int) (newWidth / roomView.getK()));
            }
        } else if (elementBeingResized != null) {
            // Moving a single element.
            double scaleX = (dragEndPoint.getX() - dragStartPoint.getX()) / dragStartPoint.getX();
            double scaleY = (dragEndPoint.getY() - dragStartPoint.getY()) / dragStartPoint.getY();

            Rectangle2D initialBounds = initialBoundsList.get(0);

            double newWidth = initialBounds.getWidth() * (1 + scaleX);
            double newHeight = initialBounds.getHeight() * (1 + scaleY);

            elementBeingResized.setBounds(new Rectangle(
                    (int) initialBounds.getX(),
                    (int) initialBounds.getY(),
                    (int) newWidth,
                    (int) newHeight
            ));

            dragStartPoint = dragEndPoint; // Update start point for next drag.
        }

        // Repaint for live updates.
        roomView.getjPanelRoom().revalidate();
        roomView.getjPanelRoom().repaint();
    }

    public void myMousePressed(RoomView roomView, MouseEvent e) {
        dragStartPoint = e.getPoint();

        if (roomView.getSelectedElements().size() == 1) {
            RoomElement selectedElement = roomView.getSelectedElements().get(0);
            if (isNearBottomRightCorner(selectedElement.getBounds(), dragStartPoint)) {
                elementsBeingResized.add(selectedElement);
                initialBoundsList.add((Rectangle2D) selectedElement.getBounds().clone());
            } else if (selectedElement.getBounds().contains(dragStartPoint)) {
                elementBeingResized = selectedElement;
            }
        } else if (!roomView.getSelectedElements().isEmpty()) {
            for (RoomElement element : roomView.getSelectedElements()) {
                if (isNearBottomRightCorner(element.getBounds(), dragStartPoint)) {
                    elementsBeingResized.addAll(roomView.getSelectedElements());
                    for (RoomElement el : elementsBeingResized) {
                        initialBoundsList.add((Rectangle2D) el.getBounds().clone());
                    }
                    break;
                }
            }
        }
    }

    private boolean isNearBottomRightCorner(Rectangle2D bounds, Point mousePoint) {
        final int cornerSize = 10;
        double xDistance = mousePoint.getX() - bounds.getMaxX();
        double yDistance = mousePoint.getY() - bounds.getMaxY();
        return Math.abs(xDistance) <= cornerSize && Math.abs(yDistance) <= cornerSize;
    }

    @Override
    public void update(Object o) {
        // No specific update behavior needed for resizing.
    }
}
