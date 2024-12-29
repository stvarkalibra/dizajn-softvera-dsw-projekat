package raf.draft.dsw.model.nodes;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.ArrayList;
import java.util.List;
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

    //----------------------

    private final List<Runnable> listeners = new ArrayList<>();

    public void addListener(Runnable listener) {
        listeners.add(listener);
    }

    private void notifyListeners() {
        for (Runnable listener : listeners) {
            listener.run();
        }
    }

    public void setNodeName(String nodeName) {
        this.nodeName = nodeName;
        notifyListeners();
    }

    //----------------------
    @JsonIgnore
    public DraftNode getParent() {
        return parent;
    }

    public void setParent(DraftNode parent) {
        this.parent = parent;
    }
}
