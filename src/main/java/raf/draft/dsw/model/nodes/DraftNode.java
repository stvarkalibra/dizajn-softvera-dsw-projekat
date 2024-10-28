package raf.draft.dsw.model.nodes;

import java.util.Objects;

public abstract class DraftNode {
    private String nodeName;
    private DraftNode parent;

    public DraftNode(String nodeName, DraftNode parent) {
        this.nodeName = nodeName;
        this.parent = parent;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DraftNode draftNode = (DraftNode) o;
        return Objects.equals(parent, draftNode.parent);
    }

    public String getNodeName() {
        return nodeName;
    }

    public void setNodeName(String nodeName) {
        this.nodeName = nodeName;
    }

    public DraftNode getParent() {
        return parent;
    }

    public void setParent(DraftNode parent) {
        this.parent = parent;
    }
}
