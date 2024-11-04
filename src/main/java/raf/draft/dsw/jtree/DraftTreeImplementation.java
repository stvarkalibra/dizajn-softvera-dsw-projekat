package raf.draft.dsw.jtree;

import raf.draft.dsw.jtree.model.DraftTreeItem;
import raf.draft.dsw.jtree.view.DraftTreeView;
import raf.draft.dsw.model.nodes.DraftNode;
import raf.draft.dsw.model.nodes.DraftNodeComposite;
import raf.draft.dsw.model.structures.Project;
import raf.draft.dsw.model.structures.ProjectExplorer;

import javax.swing.*;
import javax.swing.tree.DefaultTreeModel;
import java.util.Random;

public class DraftTreeImplementation implements DraftTree{
    private DraftTreeView treeView;
    private DefaultTreeModel treeModel;
    private static int projectNumber=0;

    @Override
    public DraftTreeView generateTree(ProjectExplorer projectExplorer) {
        DraftTreeItem root=new DraftTreeItem(projectExplorer);
        treeModel = new DefaultTreeModel(root);
        treeView =new DraftTreeView(treeModel);
        return treeView;
    }

    @Override
    public void addChild(DraftTreeItem parent) {
        if (!(parent.getDraftNode() instanceof DraftNodeComposite))
            return;

        DraftNode child = createChild(parent.getDraftNode());
        parent.add(new DraftTreeItem(child));
        ((DraftNodeComposite) parent.getDraftNode()).addChild(child);
        treeView.expandPath(treeView.getSelectionPath());
        SwingUtilities.updateComponentTreeUI(treeView);
    }

    @Override
    public DraftTreeItem getSelectedNode() {
        return (DraftTreeItem)treeView.getLastSelectedPathComponent();
    }

    private DraftNode createChild(DraftNode parent){
        if (parent instanceof ProjectExplorer)
            projectNumber++;
            return new Project("Project" + projectNumber,parent, "Project" + projectNumber, "User", " ");
    }

    public DraftTreeView getTreeView() {
        return treeView;
    }

    public void setTreeView(DraftTreeView treeView) {
        this.treeView = treeView;
    }

    public DefaultTreeModel getTreeModel() {
        return treeModel;
    }

    public void setTreeModel(DefaultTreeModel treeModel) {
        this.treeModel = treeModel;
    }
}
