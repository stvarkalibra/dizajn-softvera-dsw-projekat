package raf.draft.dsw.jtree;

import raf.draft.dsw.core.ActionManager;
import raf.draft.dsw.gui.swing.MainFrame;
import raf.draft.dsw.jtree.controller.DraftTreeFactory;
import raf.draft.dsw.jtree.model.DraftTreeItem;
import raf.draft.dsw.jtree.view.DraftTreeView;
import raf.draft.dsw.model.nodes.DraftNode;
import raf.draft.dsw.model.nodes.DraftNodeComposite;
import raf.draft.dsw.model.structures.Building;
import raf.draft.dsw.model.structures.Project;
import raf.draft.dsw.model.structures.ProjectExplorer;
import raf.draft.dsw.model.structures.Room;

import javax.swing.*;
import javax.swing.tree.DefaultTreeModel;
import java.util.Random;

public class DraftTreeImplementation implements DraftTree{
    private DraftTreeView treeView;
    private DefaultTreeModel treeModel;
    private static int projectNumber=0;
    private static int buildingNumber=0;
    private static int roomNumber=0;

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
        DraftTreeFactory draftTreeFactory = new DraftTreeFactory();
        DraftNode child = draftTreeFactory.createChild(parent.getDraftNode());
        parent.add(new DraftTreeItem(child));
        ((DraftNodeComposite) parent.getDraftNode()).addChild(child);
        treeView.expandPath(treeView.getSelectionPath());
        SwingUtilities.updateComponentTreeUI(treeView);
    }

    @Override
    public void removeChild(DraftTreeItem parent) {
        if (!(parent.getDraftNode() instanceof DraftNodeComposite)){
            ((DraftTreeItem)parent.getParent()).remove(parent);
        }else{
            parent.removeAllChildren();
            ((DraftTreeItem)parent.getParent()).remove(parent);
        }
        treeView.expandPath(treeView.getSelectionPath());
        SwingUtilities.updateComponentTreeUI(treeView);
    }

    @Override
    public DraftTreeItem getSelectedNode() {
        return (DraftTreeItem)treeView.getLastSelectedPathComponent();
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
