package raf.draft.dsw.controller.actions.stateactions;

import raf.draft.dsw.controller.actions.AbstractRoomAction;
import raf.draft.dsw.elements.RoomElement;
import raf.draft.dsw.gui.swing.MainFrame;
import raf.draft.dsw.jtabbedpane.view.RoomView;
import raf.draft.dsw.model.structures.Room;

import java.awt.event.ActionEvent;

public class RotateLeftAction extends AbstractRoomAction {
    public RotateLeftAction() {
        putValue(SMALL_ICON, loadIcon("/images/editProject.png"));
        putValue(NAME, "ChangeProjectInfo");
        putValue(SHORT_DESCRIPTION, "Change project info");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        int index= MainFrame.getInstance().getjTabbedPaneImplementation().getjTabbedPane().getSelectedIndex();
        Object o=MainFrame.getInstance().getjTabbedPaneImplementation().getRoomList().get(index);
        if(o instanceof Room) {
            Room room = (Room)o;
            for(RoomElement roomElement : room.getRoomView().getSelectedElements()){
                System.out.println(roomElement);
                roomElement.addSubscriber(room.getRoomView());
                roomElement.setRotation(roomElement.getRotation()-1);
            }
        }
    }
}
