package raf.draft.dsw.jtree.view;

import raf.draft.dsw.jtree.model.DraftTreeItem;
import raf.draft.dsw.model.nodes.DraftNode;
import raf.draft.dsw.model.structures.Building;
import raf.draft.dsw.model.structures.Project;
import raf.draft.dsw.model.structures.ProjectExplorer;
import raf.draft.dsw.model.structures.Room;

import javax.swing.*;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeCellRenderer;
import java.awt.*;

public class DraftTreeCellRender extends DefaultTreeCellRenderer {

    private final Icon roomIcon = new ImageIcon(new ImageIcon(getClass().getResource("/images/room.png")).getImage().getScaledInstance(16, 16, Image.SCALE_SMOOTH));

    private final Icon buildingIcon = new ImageIcon(new ImageIcon(getClass().getResource("/images/building.png")).getImage().getScaledInstance(16, 16, Image.SCALE_SMOOTH));

    private final Icon projectIcon = new ImageIcon(new ImageIcon(getClass().getResource("/images/project.png")).getImage().getScaledInstance(16, 16, Image.SCALE_SMOOTH));

    private final Icon projectExplorerIcon = new ImageIcon(new ImageIcon(getClass().getResource("/images/projectExplorer.png")).getImage().getScaledInstance(16, 16, Image.SCALE_SMOOTH));


    @Override
    public Component getTreeCellRendererComponent(JTree tree, Object value, boolean selected, boolean expanded, boolean leaf, int row, boolean hasFocus) {

        super.getTreeCellRendererComponent(tree, value, selected, expanded, leaf, row, hasFocus);

        if (value instanceof DraftTreeItem) {
            DraftTreeItem item = (DraftTreeItem) value;
            DraftNode draftNode = item.getDraftNode();

            if (draftNode instanceof ProjectExplorer) {
                setIcon(projectExplorerIcon);
            } else if (draftNode instanceof Project) {
                setIcon(projectIcon);
            } else if (draftNode instanceof Building) {
                setIcon(buildingIcon);
            } else if (draftNode instanceof Room) {
                setIcon(roomIcon);
            }
        }

        return this;
    }
}