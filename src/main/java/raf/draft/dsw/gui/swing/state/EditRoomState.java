package raf.draft.dsw.gui.swing.state;

import raf.draft.dsw.controller.actions.AbstractRoomAction;
import raf.draft.dsw.controller.actions.EditStateAction;
import raf.draft.dsw.gui.swing.MainFrame;
import raf.draft.dsw.jtabbedpane.view.RoomView;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import static javax.swing.Action.SMALL_ICON;

public class EditRoomState extends AbstractRoomAction implements State{

    private RoomView roomView;

    @Override
    public void myMouseClicked() {
        roomView.getjPanelRoom().addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if(MainFrame.getInstance().getStateManager().getCurrentState() instanceof EditRoomState){

                }
            }
        });
    }

    @Override
    public void myMouseScroll() {

    }

    @Override
    public void myMouseUnClicked() {

    }

    @Override
    public void myMouseDragged() {

    }
}
