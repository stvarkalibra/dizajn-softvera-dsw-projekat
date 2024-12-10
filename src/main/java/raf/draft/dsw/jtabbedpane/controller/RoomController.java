package raf.draft.dsw.jtabbedpane.controller;

import raf.draft.dsw.gui.swing.MainFrame;
import raf.draft.dsw.jtabbedpane.view.RoomView;
import raf.draft.dsw.model.structures.Room;

import javax.swing.*;

public class RoomController {

    public RoomController() {
    }

    public void dimensions(Room room){
        room.setHeight(room.getRoomView().getHeight());
        room.setWidth(room.getRoomView().getHeight());
        room.notifySubscriber(room);
    }
}
