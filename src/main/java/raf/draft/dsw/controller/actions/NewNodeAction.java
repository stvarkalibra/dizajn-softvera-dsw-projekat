package raf.draft.dsw.controller.actions;

import com.sun.tools.javac.Main;
import raf.draft.dsw.gui.swing.MainFrame;
import raf.draft.dsw.jtree.model.DraftTreeItem;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

public class NewNodeAction extends AbstractRoomAction{
    public NewNodeAction(){
        putValue(SMALL_ICON, loadIcon("/images/addNode.png"));
        putValue(NAME, "AddNode");
        putValue(SHORT_DESCRIPTION, "Node");
    }
    public void actionPerformed(ActionEvent arg0) {
        DraftTreeItem selected = (DraftTreeItem) MainFrame.getInstance().getDraftTree().getSelectedNode();
        MainFrame.getInstance().getDraftTree().addChild(selected);
    }
}
