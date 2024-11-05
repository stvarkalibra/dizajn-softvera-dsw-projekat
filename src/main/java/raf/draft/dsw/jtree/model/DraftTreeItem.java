package raf.draft.dsw.jtree.model;

import raf.draft.dsw.model.nodes.DraftNode;
import raf.draft.dsw.model.nodes.DraftNodeComposite;

import javax.swing.tree.DefaultMutableTreeNode;
import java.util.List;

public class DraftTreeItem extends DefaultMutableTreeNode {
    private DraftNode draftNode;
    public DraftTreeItem(DraftNode node){
        this.draftNode=node;
    }
    public List<DraftNode> A(DraftNodeComposite node){
        return node.getChildrenList();
    }

    @Override
    public String toString() {
        return draftNode.getNodeName();
    }
    public void setName(String name){
        this.draftNode.setNodeName(name);
    }

    public DraftNode getDraftNode() {
        return draftNode;
    }

    public void setDraftNode(DraftNode draftNode) {
        this.draftNode = draftNode;
    }
}
