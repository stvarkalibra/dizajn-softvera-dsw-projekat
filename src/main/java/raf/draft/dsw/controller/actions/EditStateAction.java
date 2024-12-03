package raf.draft.dsw.controller.actions;

import raf.draft.dsw.gui.swing.MainFrame;
import raf.draft.dsw.gui.swing.state.EditRoomState;
import raf.draft.dsw.gui.swing.state.StateManager;

import java.awt.event.ActionEvent;

public class EditStateAction extends AbstractRoomAction{

    public EditStateAction() {
        putValue(SMALL_ICON, loadIcon("/images/editProject.png"));
        putValue(NAME, "ChangeProjectInfo");
        putValue(SHORT_DESCRIPTION, "Change project info");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        MainFrame.getInstance().getStateManager().setEditRoomState();
    }
}
