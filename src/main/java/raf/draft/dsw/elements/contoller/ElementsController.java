package raf.draft.dsw.elements.contoller;

import raf.draft.dsw.elements.RoomElement;

import java.awt.*;

public class ElementsController {
    private int length;
    private int width;
    private double k;

    public ElementsController(double k) {
        this.k = k;
    }

    public void dimensions(RoomElement roomElement){
        roomElement.setDimensionWidth(width,k);
        roomElement.setDimensionLength(length,k);
    }
    public void location(RoomElement roomElement,Point location){
        roomElement.setLocation(location);
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }
}
