package raf.draft.dsw.model.repository;

import raf.draft.dsw.model.structures.ProjectExplorer;

public class DraftExplorerImplementation implements IDraftRepository {
    private ProjectExplorer projectExplorer;

    public DraftExplorerImplementation() {
        projectExplorer=new ProjectExplorer("My Project Explorer");
    }



    @Override
    public void GetRoot() {

    }

    public ProjectExplorer getProjectExplorer() {
        return projectExplorer;
    }

    public void setProjectExplorer(ProjectExplorer projectExplorer) {
        this.projectExplorer = projectExplorer;
    }
}
