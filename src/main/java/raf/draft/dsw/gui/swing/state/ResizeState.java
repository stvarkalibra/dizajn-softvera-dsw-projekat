package raf.draft.dsw.gui.swing.state;

import raf.draft.dsw.elements.RoomElement;
import raf.draft.dsw.jtabbedpane.view.RoomView;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.geom.Rectangle2D;

public class ResizeState implements State {
    private Point dragStartPoint;
    private RoomElement elementBeingResized;
    private Rectangle2D initialBounds;

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
        if (elementBeingResized != null) {
            // Finalize resizing and clear the reference.
            elementBeingResized = null;
            dragStartPoint = null;
            initialBounds = null;
        }
    }

    @Override
    public void myMouseDragged(RoomView roomView, MouseEvent e) {
        if (elementBeingResized != null) {
            Point dragEndPoint = e.getPoint();

            // Update bounds only if the mouse is near the corner
            if (isNearBottomRightCorner(elementBeingResized.getBounds(), dragEndPoint)) {
                double newWidth = dragEndPoint.getX() - elementBeingResized.getBounds().getX();
                double newHeight = dragEndPoint.getY() - elementBeingResized.getBounds().getY();

                // Update the bounds with new width and height
                elementBeingResized.setBounds(new Rectangle2D.Double(
                        elementBeingResized.getBounds().getX(),
                        elementBeingResized.getBounds().getY(),
                        newWidth,
                        newHeight
                ));
                System.out.println(elementBeingResized.getDimensionWidth() + " " + elementBeingResized.getDimensionLength());

                elementBeingResized.setDimensionLenght((int)(newHeight/roomView.getK()));
                elementBeingResized.setDimensionWidth((int)(newWidth/roomView.getK()));

                System.out.println(elementBeingResized.getDimensionWidth() + " " + elementBeingResized.getDimensionLength());

                // Optionally log to debug
                System.out.println("Resizing Element to Width: " + newWidth + ", Height: " + newHeight);

                // Request repaint to show live resizing
                roomView.repaint();
            }
        }
    }



    public void myMousePressed(RoomView roomView, MouseEvent e) {
        for (RoomElement element : roomView.getSelectedElements()) {
            if (element.getBounds() != null && isNearBottomRightCorner(element.getBounds(), e.getPoint())) {
                System.out.println("BLIZU");
                elementBeingResized = element;
                initialBounds = element.getBounds();
                dragStartPoint = e.getPoint();
                break;
            }
        }
    }
    private boolean isNearBottomRightCorner(Rectangle2D bounds, Point mousePoint) {
        final int cornerSize = 10;

        double xDistance = mousePoint.getX() - bounds.getMaxX();
        double yDistance = mousePoint.getY() - bounds.getMaxY();
        System.out.println(Math.abs(xDistance) <= cornerSize && Math.abs(yDistance) <= cornerSize);
        System.out.println("Mouse Position: " + mousePoint);
        System.out.println("Element Bounds: " + bounds);
        return Math.abs(xDistance) <= cornerSize && Math.abs(yDistance) <= cornerSize;
    }

    @Override
    public void update(Object o) {
        // No specific update behavior needed for resizing.
    }
}
