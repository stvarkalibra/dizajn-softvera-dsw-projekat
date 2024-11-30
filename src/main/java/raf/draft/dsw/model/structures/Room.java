package raf.draft.dsw.model.structures;

import raf.draft.dsw.model.nodes.DraftNode;
import raf.draft.dsw.model.nodes.DraftNodeComposite;

public class Room extends DraftNodeComposite {
    int size;
    public Room(String nodeName, DraftNode parent) {
        super(nodeName, parent);
        //size=this.size;

    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }
}
