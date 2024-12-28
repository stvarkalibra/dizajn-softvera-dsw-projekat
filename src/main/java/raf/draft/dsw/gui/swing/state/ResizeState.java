package raf.draft.dsw.gui.swing.state;

import raf.draft.dsw.commandPattern.ResizeCommand;
import raf.draft.dsw.elements.RoomElement;
import raf.draft.dsw.jtabbedpane.view.RoomView;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelEvent;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import java.util.List;

public class ResizeState implements State {
    private Point dragStartPoint;
    private Point dragEndPoint;
    private List<RoomElement> elementsBeingResized = new ArrayList<>();
    private List<Rectangle2D> initialBoundsList = new ArrayList<>();
    private List<Integer> oldWidth=new ArrayList<>();
    private List<Integer> oldHeight=new ArrayList<>();
    private List<Integer> newWidth=new ArrayList<>();
    private List<Integer> newHeight=new ArrayList<>();
    private RoomElement elementBeingResized;
    private boolean mousePressed=false;
    @Override
    public void myMouseClicked(RoomView roomView, Point location) {
    }

    @Override
    public void myMouseScrolled(RoomView roomView, MouseWheelEvent event) {
    }

    @Override
    public void myMouseReleased(RoomView roomView) {
        boolean hasCollision = false;
        for (RoomElement resizedElement : elementsBeingResized) {
            System.out.println(newWidth.size());
            System.out.println(elementsBeingResized.indexOf(resizedElement));
            if(newWidth.size()-1>=elementsBeingResized.indexOf(resizedElement)){
                newWidth.set(elementsBeingResized.indexOf(resizedElement),resizedElement.getDimensionWidth());
                newHeight.set(elementsBeingResized.indexOf(resizedElement),resizedElement.getDimensionLenght());
            }else{
                newWidth.add(resizedElement.getDimensionWidth());
                newHeight.add(resizedElement.getDimensionLenght());
            }

            for (RoomElement otherElement : roomView.getRoom().getRoomElements()) {
                if (resizedElement == otherElement) continue;
                if (resizedElement.getBounds().intersects(otherElement.getBounds())) {
                    hasCollision = true;
                    break;
                }
            }
            if (hasCollision) {
                JOptionPane.showMessageDialog(
                        null,
                        "2_ELEMENTS_ARE_INTERSECTING",
                        "Error",
                        JOptionPane.ERROR_MESSAGE
                );
                break;
            }
        }
        if (hasCollision) {
            for (int i = 0; i < elementsBeingResized.size(); i++) {
                RoomElement element = elementsBeingResized.get(i);
                Rectangle2D initialBounds = initialBoundsList.get(i);

                element.setBounds(new Rectangle(
                        (int)initialBounds.getX(),
                        (int)initialBounds.getY(),
                        (int)initialBounds.getWidth(),
                        (int)initialBounds.getHeight()
                ));
                element.setDimensionWidth((int) (initialBounds.getWidth() / roomView.getK()), roomView.getK());
                element.setDimensionLength((int) (initialBounds.getHeight() / roomView.getK()), roomView.getK());
                element.addSubscriber(roomView);
                element.notifySubscriber(element);
            }
        }

        ResizeCommand resizeCommand=new ResizeCommand(roomView, elementsBeingResized,oldWidth,oldHeight,newWidth,newHeight);
        roomView.getCommandManager().addCommand(resizeCommand);
        dragStartPoint=null;
        dragEndPoint = null;
        elementsBeingResized.clear();
        initialBoundsList.clear();
        elementBeingResized = null;
    }


    @Override
    public void myMouseDragged(RoomView roomView, MouseEvent e) {
        dragEndPoint = e.getPoint();
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

                element.setDimensionLength((int) (newHeight / roomView.getK()),roomView.getK());
                element.setDimensionWidth((int) (newWidth / roomView.getK()), roomView.getK());
            }
        } else if (elementBeingResized != null) {
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

            dragStartPoint = dragEndPoint;
        }
        roomView.getjPanelRoom().revalidate();
        roomView.getjPanelRoom().repaint();
    }

    public void myMousePressed(RoomView roomView, MouseEvent e) {
        dragStartPoint = e.getPoint();
        elementsBeingResized.clear();
        initialBoundsList.clear();
        elementBeingResized = null;

        if (roomView.getSelectedElements().size() == 1) {
            RoomElement selectedElement = roomView.getSelectedElements().get(0);
            if (isNearBottomRightCorner(selectedElement.getBounds(), dragStartPoint)) {
                elementsBeingResized.add(selectedElement);
                initialBoundsList.add((Rectangle2D) selectedElement.getBounds().clone());
                oldWidth.add(selectedElement.getDimensionWidth());
                oldHeight.add(selectedElement.getDimensionLenght());
                elementBeingResized = selectedElement;
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
                    elementBeingResized = element;
                    break;
                }
            }
        }
        if (elementBeingResized != null && !mousePressed) {
            mousePressed = true;
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

    }
}
