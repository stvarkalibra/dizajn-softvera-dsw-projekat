package raf.draft.dsw.controller.actions.stateactions;

import raf.draft.dsw.controller.actions.AbstractRoomAction;
import raf.draft.dsw.gui.swing.MainFrame;
import raf.draft.dsw.model.structures.Room;

import java.awt.event.ActionEvent;

public class SelectAction extends AbstractRoomAction {
    public SelectAction() {
        putValue(SMALL_ICON, loadIcon("/images/select.png"));
        putValue(NAME, "SelectElements");
        putValue(SHORT_DESCRIPTION, "Select elements");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        int index = MainFrame.getInstance().getjTabbedPaneImplementation().getjTabbedPane().getSelectedIndex();
        Room room = MainFrame.getInstance().getjTabbedPaneImplementation().getRoomList().get(index);
        room.getRoomView().startSelect();
    }
}
