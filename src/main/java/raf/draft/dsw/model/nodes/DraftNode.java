package raf.draft.dsw.model.nodes;

public abstract class DraftNode {
    private String nodeName;
    private DraftNode parent;

    public DraftNode(String nodeName) {
        this.nodeName = nodeName;
    }
}
