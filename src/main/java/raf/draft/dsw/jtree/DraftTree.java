package raf.draft.dsw.jtree;

import raf.draft.dsw.jtree.model.DraftTreeItem;
import raf.draft.dsw.jtree.view.DraftTreeView;
import raf.draft.dsw.model.structures.ProjectExplorer;

public interface DraftTree {
    DraftTreeView generateTree(ProjectExplorer projectExplorer);
    void addChild(DraftTreeItem parent);
    DraftTreeItem getSelectedNode();
}
