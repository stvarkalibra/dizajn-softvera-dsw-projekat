package raf.draft.dsw.commandPattern;

import raf.draft.dsw.elements.RoomElement;
import raf.draft.dsw.gui.swing.painters.MyPainter;
import raf.draft.dsw.jtabbedpane.view.RoomView;
import raf.draft.dsw.model.structures.Room;

import java.util.Iterator;

public class CopyPasteCommand extends AbstractCommand{
    private RoomElement roomElement;
    private MyPainter painter;
    private Room room;
    public CopyPasteCommand(RoomView roomView, RoomElement roomElement, MyPainter painter, Room room) {
        super(roomView);
        this.roomElement = roomElement;
        this.painter = painter;
        this.room=room;
    }

    @Override
    public void doCommand() {
        room.addChild(roomElement);
        room.getRoomView().addElementToTree(roomElement);
        room.getRoomElements().add(roomElement);
        room.getChildrenList().add(roomElement);

        room.getRoomView().getPainterList().add(painter);
        roomElement.addSubscriber(room.getRoomView());
        roomElement.notifySubscriber(roomElement);
    }

    @Override
    public void undoCommand() {
        Iterator<RoomElement> roomElementIterator = roomView.getRoom().getRoomElements().iterator();
        while (roomElementIterator.hasNext()) {
            RoomElement roomElement = roomElementIterator.next();
            if (roomElement.equals(this.roomElement)) {
                roomElementIterator.remove();
                roomView.getRoom().getChildrenList().remove(roomElement);
                Iterator<MyPainter> painterIterator = roomView.getPainterList().iterator();
                while (painterIterator.hasNext()) {
                    MyPainter painter = painterIterator.next();
                    if (painter.getRoomElement().equals(roomElement)) {
                        painterIterator.remove();
                        break;
                    }
                }
            }
            roomView.getRoom().notifySubscriber(roomElement);
        }
        roomView.getjPanelRoom().revalidate();
        roomView.getjPanelRoom().repaint();
    }
}
