package raf.draft.dsw.model.elements;

import raf.draft.dsw.model.nodes.DraftNode;

import java.awt.*;

public abstract class RoomElement extends DraftNode {
    private int location;
    private int dimension;
    private int rotation;

    public RoomElement(String nodeName, DraftNode parent, int location, int dimension, int rotation) {
        super(nodeName, parent);
        this.location = location;
        this.dimension = dimension;
        this.rotation = rotation%3;
    }
}
