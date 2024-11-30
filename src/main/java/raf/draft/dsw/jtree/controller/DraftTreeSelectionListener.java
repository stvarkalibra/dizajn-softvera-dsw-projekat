package raf.draft.dsw.jtree.controller;

import raf.draft.dsw.controller.observer.ISubscriber;
import raf.draft.dsw.gui.swing.MainFrame;
import raf.draft.dsw.jtree.model.DraftTreeItem;
import raf.draft.dsw.model.structures.Project;

import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.TreePath;

public class DraftTreeSelectionListener implements TreeSelectionListener {
    @Override
    public void valueChanged(TreeSelectionEvent e) {
        MainFrame.getInstance().setFlag(true);
        TreePath path=e.getPath();
        DraftTreeItem draftTreeItem=(DraftTreeItem)path.getLastPathComponent();
        System.out.println("Selektovan cvor:"+ draftTreeItem.getDraftNode().getNodeName());
        System.out.println("getPath: "+e.getPath());
    }


}
