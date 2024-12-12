package raf.draft.dsw.controller.actions.stateactions;

import raf.draft.dsw.controller.actions.AbstractRoomAction;
import raf.draft.dsw.elements.RoomElement;
import raf.draft.dsw.gui.swing.MainFrame;
import raf.draft.dsw.model.structures.Room;

import java.awt.event.ActionEvent;

import static javax.swing.Action.SHORT_DESCRIPTION;
import static javax.swing.Action.SMALL_ICON;

public class RotateRightAction extends AbstractRoomAction {
    public RotateRightAction() {
        putValue(SMALL_ICON, loadIcon("/images/rotateRight.png"));
        putValue(NAME, "RotateToRight");
        putValue(SHORT_DESCRIPTION, "Rotate to right");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        int index= MainFrame.getInstance().getjTabbedPaneImplementation().getjTabbedPane().getSelectedIndex();
        Object o=MainFrame.getInstance().getjTabbedPaneImplementation().getRoomList().get(index);
        if(o instanceof Room) {
            Room room = (Room)o;
            room.addSubscriber(room.getRoomView());
            for(RoomElement roomElement : room.getRoomView().getSelectedElements()){
                roomElement.setRotation(roomElement.getRotation()+1);
            }
        }
    }
}
