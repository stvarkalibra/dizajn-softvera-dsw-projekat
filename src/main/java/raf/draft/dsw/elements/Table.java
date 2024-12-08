package raf.draft.dsw.elements;

import raf.draft.dsw.model.nodes.DraftNode;

import java.awt.*;

public class Table extends RoomElement{
    public Table(String nodeName, DraftNode parent, Point location, int dimensionWidth, int dimensionLenght, int rotation) {
        super(nodeName, parent, location, dimensionWidth, dimensionLenght, rotation);
    }

    @Override
    public Point getLocation() {
        return super.getLocation();
    }

    @Override
    public void setLocation(Point location) {
        super.setLocation(location);
    }

    @Override
    public int getDimensionWidth() {
        return super.getDimensionWidth();
    }

    @Override
    public void setDimensionWidth(int dimensionWidth) {
        super.setDimensionWidth(dimensionWidth);
    }

    @Override
    public int getDimensionLenght() {
        return super.getDimensionLenght();
    }

    @Override
    public void setDimensionLenght(int dimensionLenght) {
        super.setDimensionLenght(dimensionLenght);
    }

    @Override
    public int getRotation() {
        return super.getRotation();
    }

    @Override
    public void setRotation(int rotation) {
        super.setRotation(rotation);
    }
}
