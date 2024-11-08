package raf.draft.dsw.controller.actions;

import raf.draft.dsw.gui.swing.ChangeInfoFrame;
import raf.draft.dsw.gui.swing.MainFrame;
import raf.draft.dsw.jtree.model.DraftTreeItem;
import raf.draft.dsw.model.structures.Project;
import raf.draft.dsw.model.structures.Room;

import java.awt.event.ActionEvent;

public class ChangeInfoAction extends AbstractRoomAction{

    public ChangeInfoAction() {
        putValue(SMALL_ICON, loadIcon("/images/editProject.png"));
        putValue(NAME, "AddNode");
        putValue(SHORT_DESCRIPTION, "Node");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        DraftTreeItem selected = (DraftTreeItem) MainFrame.getInstance().getDraftTree().getSelectedNode();
        if (selected.getDraftNode() instanceof Project){
            ChangeInfoFrame changeInfoFrame = new ChangeInfoFrame((Project) selected.getDraftNode());
            changeInfoFrame.addElements((Project) selected.getDraftNode());
            changeInfoFrame.setVisible(true);

        }
    }
}
