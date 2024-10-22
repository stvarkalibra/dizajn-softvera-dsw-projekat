package raf.draft.dsw.model.structures;

import raf.draft.dsw.model.nodes.DraftNode;
import raf.draft.dsw.model.nodes.DraftNodeComposite;

public class Building extends DraftNodeComposite {
    public Building(String nodeName) {
        super(nodeName);
    }

    @Override
    public void addChild(DraftNode node) {
        super.addChild(node);
    }
}
