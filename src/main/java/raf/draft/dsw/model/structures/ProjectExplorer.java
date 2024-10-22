package raf.draft.dsw.model.structures;

import raf.draft.dsw.model.nodes.DraftNode;
import raf.draft.dsw.model.nodes.DraftNodeComposite;
import raf.draft.dsw.model.repository.DraftExplorerImplementation;

public class ProjectExplorer extends DraftNodeComposite {
    public ProjectExplorer(String nodeName) {
        super(nodeName);
    }

    @Override
    public void addChild(DraftNode node) {
        super.addChild(node);
    }

    @Override
    public void removeChild(DraftNode node) {
        super.removeChild(node);
    }
}
