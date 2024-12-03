package raf.draft.dsw.model.elements;

import raf.draft.dsw.model.nodes.DraftNode;

public class Door extends RoomElement{
    public Door(String nodeName, DraftNode parent, int location, int dimension, int rotation) {
        super(nodeName, parent, location, dimension, rotation);
    }
}
