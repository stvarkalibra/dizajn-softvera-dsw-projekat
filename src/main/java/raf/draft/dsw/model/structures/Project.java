package raf.draft.dsw.model.structures;

import raf.draft.dsw.model.nodes.DraftNode;
import raf.draft.dsw.model.nodes.DraftNodeComposite;

public class Project extends DraftNodeComposite {
    private String projectName;
    private String projectAutor;
    private String pathToFolder;

    public Project(String nodeName, String projectName, String projectAutor, String pathToFolder) {
        super(nodeName);
        this.projectName = projectName;
        this.projectAutor = projectAutor;
        this.pathToFolder = pathToFolder;
    }

    @Override
    public void addChild(DraftNode node) {
        super.addChild(node);
    }

    @Override
    public void removeChild(DraftNode node) {
        super.removeChild(node);
    }
}
