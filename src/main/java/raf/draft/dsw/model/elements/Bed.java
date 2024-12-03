package raf.draft.dsw.model.elements;

import raf.draft.dsw.model.nodes.DraftNode;

public class Bed extends RoomElement{
    public Bed(String nodeName, DraftNode parent, int location, int dimension, int rotation) {
        super(nodeName, parent, location, dimension, rotation);
    }
}
