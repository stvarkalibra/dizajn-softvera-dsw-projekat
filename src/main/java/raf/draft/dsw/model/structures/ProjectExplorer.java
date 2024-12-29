package raf.draft.dsw.model.structures;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import raf.draft.dsw.model.nodes.DraftNode;
import raf.draft.dsw.model.nodes.DraftNodeComposite;
import raf.draft.dsw.model.repository.DraftExplorerImplementation;

import java.util.List;

public class ProjectExplorer extends DraftNodeComposite {
    public ProjectExplorer(String nodeName) {
        super(nodeName, null);
    }

    @Override
    public void addChild(DraftNode node) {
        if (node instanceof Project){
            Project project = (Project) node;
            if (!this.getChildrenList().contains(project)){
                this.getChildrenList().add(project);
            }
        }
    }

    @Override
    public void removeChild(DraftNode node) {
        if (node instanceof Project){
            Project project = (Project) node;
            if (this.getChildrenList().contains(project)){
                this.getChildrenList().remove(project);
            }
        }
    }
    @JsonManagedReference
    @Override
    public List<DraftNode> getChildrenList() {
        return super.getChildrenList();
    }
}
