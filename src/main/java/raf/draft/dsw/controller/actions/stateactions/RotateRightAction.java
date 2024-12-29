package raf.draft.dsw.controller.actions.stateactions;

import raf.draft.dsw.commandPattern.RotateCommand;
import raf.draft.dsw.controller.actions.AbstractRoomAction;
import raf.draft.dsw.elements.RoomElement;
import raf.draft.dsw.gui.swing.MainFrame;
import raf.draft.dsw.jtabbedpane.view.RoomView;
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
                roomElement.addSubscriber(room.getRoomView());
                int oldRotation=roomElement.getRotation();
                roomElement.setRotation(oldRotation+1);
                RotateCommand rotateCommand=new RotateCommand(room.getRoomView(),roomElement.getRotation(),oldRotation,room.getRoomView().getSelectedElements());
                room.getRoomView().getCommandManager().addCommand(rotateCommand);
            }
        }
    }
}
