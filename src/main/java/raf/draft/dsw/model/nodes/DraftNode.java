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
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        DraftNode draftNode = (DraftNode) object;
        return Objects.equals(nodeName, draftNode.nodeName) && Objects.equals(parent, draftNode.parent);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nodeName, parent);
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
