package raf.draft.dsw.model.nodes;

import java.util.ArrayList;
import java.util.List;

public abstract class DraftNodeComposite extends DraftNode {
    private List<DraftNode> childrenList=new ArrayList<>();

    public DraftNodeComposite(String nodeName) {
        super(nodeName);
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

}
