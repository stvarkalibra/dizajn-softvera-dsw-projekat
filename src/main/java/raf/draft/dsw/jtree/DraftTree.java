package raf.draft.dsw.jtree;

import raf.draft.dsw.controller.observer.ISubscriber;
import raf.draft.dsw.jtree.model.DraftTreeItem;
import raf.draft.dsw.jtree.view.DraftTreeView;
import raf.draft.dsw.model.structures.ProjectExplorer;

public interface DraftTree extends ISubscriber {
    DraftTreeView generateTree(ProjectExplorer projectExplorer);
    void addChild(DraftTreeItem parent);
    void removeChild(DraftTreeItem parent,boolean deleteElement);
    DraftTreeItem getSelectedNode();

    @Override
    void update(Object o);
}
