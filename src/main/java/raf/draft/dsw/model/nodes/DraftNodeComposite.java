package raf.draft.dsw.model.nodes;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.ArrayList;
import java.util.List;

public abstract class DraftNodeComposite extends DraftNode {

    private List<DraftNode> childrenList;

    public DraftNodeComposite(String nodeName, DraftNode parent) {
        super(nodeName, parent);
        this.childrenList = new ArrayList<>();
    }

    public void addChild(DraftNode node){
        if(!childrenList.contains(node)){
            childrenList.add(node);
        }

    }
    public void removeChild(DraftNode node){
        if(childrenList.contains(node)){
            childrenList.remove(node);
        }
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
    @JsonIgnore
    @Override
    public DraftNode getParent() {
        return super.getParent();
    }
}
