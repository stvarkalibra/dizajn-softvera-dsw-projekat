package raf.draft.dsw.jtree.model;

import raf.draft.dsw.model.nodes.DraftNode;

import javax.swing.tree.DefaultMutableTreeNode;

public class DraftTreeItem extends DefaultMutableTreeNode {
    private DraftNode draftNode;
    public DraftTreeItem(DraftNode node){
        this.draftNode=node;
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
