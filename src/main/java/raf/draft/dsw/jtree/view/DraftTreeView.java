package raf.draft.dsw.jtree.view;

import raf.draft.dsw.controller.observer.ISubscriber;
import raf.draft.dsw.jtree.controller.DraftTreeCellEditor;
import raf.draft.dsw.jtree.controller.DraftTreeSelectionListener;
import raf.draft.dsw.jtree.model.DraftTreeItem;
import raf.draft.dsw.model.structures.Building;
import raf.draft.dsw.model.structures.Project;
import raf.draft.dsw.model.structures.Room;

import javax.swing.*;
import javax.swing.tree.DefaultTreeModel;

import static com.sun.java.accessibility.util.SwingEventMonitor.addTreeSelectionListener;

public class DraftTreeView extends JTree implements ISubscriber {
    public DraftTreeView(DefaultTreeModel defaultTreeModel) {
        setModel(defaultTreeModel);
        DraftTreeCellRender ruTreeCellRenderer = new DraftTreeCellRender();
        addTreeSelectionListener(new DraftTreeSelectionListener());
        setCellEditor(new DraftTreeCellEditor(this, ruTreeCellRenderer));
        setCellRenderer(ruTreeCellRenderer);
        setEditable(true);
    }

    @Override
    public void update(Object o) {
        if(o instanceof Project){
            ((Project) o).setNodeName(((Project) o).getProjectName());
        }if(o instanceof Building){
            ((Building) o).setNodeName(((Building) o).getNodeName());
        }if(o instanceof Room){
            ((Room) o).setNodeName(((Room) o).getNodeName());
        }
    }
}
