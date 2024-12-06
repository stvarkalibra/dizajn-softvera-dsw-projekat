package raf.draft.dsw.elements;

import raf.draft.dsw.model.nodes.DraftNode;

import java.awt.*;

public abstract class RoomElement extends DraftNode {
    private Point location;
    private int dimensionWidth;
    private int dimensionLenght;
    private int rotation;

    public RoomElement(String nodeName, DraftNode parent, Point location, int dimensionWidth, int dimensionLenght, int rotation) {
        super(nodeName, parent);
        this.location = location;
        this.dimensionWidth = dimensionWidth;
        this.dimensionLenght = dimensionLenght;
        this.rotation = rotation%4;
    }

    @Override
    public String toString() {
        return "RoomElement{" +
                "location=" + location +
                ", dimensionWidth=" + dimensionWidth +
                ", dimensionLenght=" + dimensionLenght +
                ", rotation=" + rotation +
                '}';
    }

    public Point getLocation() {
        return location;
    }

    public void setLocation(Point location) {
        this.location = location;
    }

    public int getDimensionWidth() {
        return dimensionWidth;
    }

    public void setDimensionWidth(int dimensionWidth) {
        this.dimensionWidth = dimensionWidth;
    }

    public int getDimensionLenght() {
        return dimensionLenght;
    }

    public void setDimensionLenght(int dimensionLenght) {
        this.dimensionLenght = dimensionLenght;
    }

    public int getRotation() {
        return rotation;
    }

    public void setRotation(int rotation) {
        this.rotation = rotation;
    }
}
