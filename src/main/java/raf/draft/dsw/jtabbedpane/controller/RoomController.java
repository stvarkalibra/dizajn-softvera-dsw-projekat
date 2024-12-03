package raf.draft.dsw.jtabbedpane.controller;

import raf.draft.dsw.gui.swing.MainFrame;
import raf.draft.dsw.jtabbedpane.view.RoomView;
import raf.draft.dsw.model.structures.Room;

import javax.swing.*;

public class RoomController {
    private RoomView roomView;
    private Room room;

    public RoomController(RoomView roomView, Room room) {
        this.roomView = roomView;
        this.room = room;
    }

    public void Dimensions(Room room){
        room.addSubscriber(roomView);
        room.setHeight(roomView.getHeight());
        room.setWidth(roomView.getWidth());
    }
}
