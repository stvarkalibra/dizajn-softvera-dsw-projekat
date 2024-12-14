package raf.draft.dsw.gui.swing.painters;

import raf.draft.dsw.elements.RoomElement;

import java.awt.*;

public abstract class MyPainter {
    private double zoomScale=1.0;
    private RoomElement roomElement;
    public MyPainter(RoomElement element) {
        this.roomElement=element;
    }

    public abstract void paint(Graphics2D g, Point startPoint, int height, int width);

    public abstract boolean elementAt(RoomElement element, Point pos);

    public RoomElement getRoomElement() {
        return roomElement;
    }

    public void setRoomElement(RoomElement roomElement) {
        this.roomElement = roomElement;
    }

    public double getZoomScale() {
        return zoomScale;
    }

    public void setZoomScale(double zoomScale) {
        this.zoomScale = zoomScale;
        System.out.println("SETOVANO" + zoomScale);
    }
}