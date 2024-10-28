package raf.draft.dsw.model.structures;

import raf.draft.dsw.model.nodes.DraftNode;
import raf.draft.dsw.model.nodes.DraftNodeComposite;

public class Building extends DraftNodeComposite {
    public Building(String nodeName, DraftNode parent) {
        super(nodeName, parent);
    }

    @Override
    public void addChild(DraftNode node) {
        if (node instanceof Room){
            Room room = (Room) node;
            if (!this.getChildrenList().contains(room)){
                this.getChildrenList().add(room);
            }
        }
    }

    @Override
    public void removeChild(DraftNode node) {
        if (node instanceof Room){
            Room room = (Room) node;
            if (this.getChildrenList().contains(room)){
                this.getChildrenList().remove(room);
            }
        }
    }
}
