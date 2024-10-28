package raf.draft.dsw.model.nodes;

import java.util.ArrayList;
import java.util.List;

public abstract class DraftNodeComposite extends DraftNode {
    private List<DraftNode> childrenList;

    public DraftNodeComposite(String nodeName, DraftNode parent) {
        super(nodeName, parent);
        this.childrenList = new ArrayList<>();
    }

    public void addChild(DraftNode node){

    }
    public void removeChild(DraftNode node){

    }

    public DraftNode getChildByName(String name){
        for(DraftNode child: childrenList){
            if (child.getNodeName().equals(name)){
                return child;
            }
        }
        return null;
    }

    public List<DraftNode> getChildrenList() {
        return childrenList;
    }
}
