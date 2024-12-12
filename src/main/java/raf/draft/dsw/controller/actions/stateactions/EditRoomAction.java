package raf.draft.dsw.controller.actions.stateactions;

import raf.draft.dsw.controller.actions.AbstractRoomAction;
import raf.draft.dsw.gui.swing.MainFrame;
import raf.draft.dsw.gui.swing.state.EditRoomState;
import raf.draft.dsw.model.structures.Room;

import java.awt.event.ActionEvent;

public class EditRoomAction extends AbstractRoomAction {
    public EditRoomAction() {
        putValue(SMALL_ICON, loadIcon("/images/editProject.png"));
        putValue(NAME, "EditRoomDimension");
        putValue(SHORT_DESCRIPTION, "Edit room dimension");
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        int index= MainFrame.getInstance().getjTabbedPaneImplementation().getjTabbedPane().getSelectedIndex();
        Object o=MainFrame.getInstance().getjTabbedPaneImplementation().getRoomList().get(index);

        if(o instanceof Room){
            ((Room) o).getRoomView().startEditRoom();
        }
    }
}
