package raf.draft.dsw.gui.swing.state;

import raf.draft.dsw.elements.Bed;
import raf.draft.dsw.elements.RoomElement;
import raf.draft.dsw.gui.swing.painters.BedPainter;
import raf.draft.dsw.gui.swing.painters.MyPainter;
import raf.draft.dsw.jtabbedpane.view.RoomView;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelEvent;

public class ZoomState implements State {
    private static final double minimal_zoom=0.5;
    private static final double maximum_zoom=2.2;
    private double zoomScale=1.0;

    @Override
    public void myMouseClicked(RoomView roomView, Point location) {
    }

    @Override
    public void myMouseScrolled(RoomView roomView, MouseWheelEvent e) {
        if (e.getWheelRotation() < 0) {
            zoomIn();
        } else {
            zoomOut();
        }
        roomView.setZoomScale(zoomScale);
        for(RoomElement roomElement:roomView.getRoom().getRoomElements()){
            roomElement.setBounds(new Rectangle(roomElement.getLocation().x,roomElement.getLocation().y,(roomElement.getDimensionWidth()*(int)roomView.getK())*(int)zoomScale,(roomElement.getDimensionLength()*(int)roomView.getK())*(int)zoomScale));
        }

        roomView.getjPanelRoom().revalidate();
        roomView.getjPanelRoom().repaint();
    }
    private void zoomIn() {
        if (zoomScale < maximum_zoom) {
            zoomScale += 0.1;
        }
    }

    private void zoomOut() {
        if (zoomScale > minimal_zoom) {
            zoomScale -= 0.1;
        }
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
