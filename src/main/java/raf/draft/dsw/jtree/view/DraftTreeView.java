package raf.draft.dsw.jtree.view;

import raf.draft.dsw.jtree.controller.DraftTreeCellEditor;
import raf.draft.dsw.jtree.controller.DraftTreeSelectionListener;

import javax.swing.*;
import javax.swing.tree.DefaultTreeModel;

import static com.sun.java.accessibility.util.SwingEventMonitor.addTreeSelectionListener;

public class DraftTreeView extends JTree {
    public DraftTreeView(DefaultTreeModel defaultTreeModel) {
        setModel(defaultTreeModel);
        DraftTreeCellRender ruTreeCellRenderer = new DraftTreeCellRender();
        addTreeSelectionListener(new DraftTreeSelectionListener());
        setCellEditor(new DraftTreeCellEditor(this, ruTreeCellRenderer));
        setCellRenderer(ruTreeCellRenderer);
        setEditable(true);
    }
}
