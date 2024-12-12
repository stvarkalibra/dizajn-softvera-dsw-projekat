package raf.draft.dsw.controller.actions.stateactions;

import raf.draft.dsw.controller.actions.AbstractRoomAction;
import raf.draft.dsw.gui.swing.MainFrame;
import raf.draft.dsw.model.structures.Room;

import java.awt.event.ActionEvent;

public class DeleteAction extends AbstractRoomAction {
    public DeleteAction() {
        putValue(SMALL_ICON, loadIcon("/images/editProject.png"));
        putValue(NAME, "ChangeProjectInfo");
        putValue(SHORT_DESCRIPTION, "Change project info");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        System.out.println("STISNUTO");
        int index= MainFrame.getInstance().getjTabbedPaneImplementation().getjTabbedPane().getSelectedIndex();
        Object o=MainFrame.getInstance().getjTabbedPaneImplementation().getRoomList().get(index);

        if(o instanceof Room){
            ((Room) o).getRoomView().startDelete();
        }
    }
}
