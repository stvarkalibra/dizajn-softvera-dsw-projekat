package raf.draft.dsw.elements;

import raf.draft.dsw.model.nodes.DraftNode;

import java.awt.*;

public class Sink extends RoomElement{
    public Sink(String nodeName, DraftNode parent, Point location, int dimensionWidth, int dimensionLenght, int rotation) {
        super(nodeName, parent, location, dimensionWidth, dimensionLenght, rotation);
    }
}
