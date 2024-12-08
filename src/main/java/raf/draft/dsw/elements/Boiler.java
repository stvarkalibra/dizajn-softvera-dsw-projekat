package raf.draft.dsw.elements;

import raf.draft.dsw.model.nodes.DraftNode;

import java.awt.*;

public class Boiler extends RoomElement{
    private static int number=0;
    public Boiler(String nodeName, DraftNode parent, Point location, int dimensionWidth, int dimensionLenght, int rotation) {
        super(nodeName, parent, location, dimensionWidth, dimensionLenght, rotation);
        number++;
        setNodeName("Boiler" + number);
    }
}
