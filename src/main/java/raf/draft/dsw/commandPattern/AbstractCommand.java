package raf.draft.dsw.commandPattern;

import raf.draft.dsw.jtabbedpane.view.RoomView;

public abstract class AbstractCommand {
    RoomView roomView;

    public AbstractCommand(RoomView roomView) {
        this.roomView = roomView;
    }

    public abstract void doCommand();
    public abstract void undoCommand();

}
