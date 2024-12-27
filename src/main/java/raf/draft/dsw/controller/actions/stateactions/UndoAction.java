package raf.draft.dsw.controller.actions.stateactions;

import raf.draft.dsw.controller.actions.AbstractRoomAction;
import raf.draft.dsw.elements.RoomElement;
import raf.draft.dsw.gui.swing.MainFrame;
import raf.draft.dsw.model.structures.Room;

import java.awt.event.ActionEvent;

public class UndoAction extends AbstractRoomAction {
    public UndoAction() {
        putValue(SMALL_ICON, loadIcon("/images/rotateLeft.png"));
        putValue(NAME, "RotateToLeft");
        putValue(SHORT_DESCRIPTION, "Rotate to left");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        int index= MainFrame.getInstance().getjTabbedPaneImplementation().getjTabbedPane().getSelectedIndex();
        Object o=MainFrame.getInstance().getjTabbedPaneImplementation().getRoomList().get(index);
        if(o instanceof Room) {
            Room room = (Room)o;
            room.getRoomView().getCommandManager().undoCommand();
            room.getRoomView().getjPanelRoom().revalidate();
            room.getRoomView().getjPanelRoom().repaint();
        }
    }
}
