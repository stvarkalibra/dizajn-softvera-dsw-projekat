package raf.draft.dsw.controller.actions;

import raf.draft.dsw.gui.swing.MainFrame;
import raf.draft.dsw.jtree.model.DraftTreeItem;

import java.awt.event.ActionEvent;

import static javax.swing.Action.SMALL_ICON;

public class RemoveNodeAction extends AbstractRoomAction{
    public RemoveNodeAction(){
        putValue(SMALL_ICON, loadIcon("/images/removeNode.png"));
        putValue(NAME, "RemoveNode");
        putValue(SHORT_DESCRIPTION, "Node");
    }
    public void actionPerformed(ActionEvent arg0) {
        DraftTreeItem selected = (DraftTreeItem) MainFrame.getInstance().getDraftTree().getSelectedNode();
        MainFrame.getInstance().getDraftTree().removeChild(selected);
    }
}
