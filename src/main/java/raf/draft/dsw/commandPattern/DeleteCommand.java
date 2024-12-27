package raf.draft.dsw.commandPattern;

import raf.draft.dsw.elements.RoomElement;
import raf.draft.dsw.gui.swing.painters.MyPainter;
import raf.draft.dsw.jtabbedpane.view.RoomView;

import java.util.Iterator;

public class DeleteCommand extends AbstractCommand{
    RoomElement roomElement;
    MyPainter painter;

    public DeleteCommand(RoomView roomView, RoomElement roomElement, MyPainter painter) {
        super(roomView);
        this.roomElement = roomElement;
        this.painter = painter;
    }

    @Override
    public void doCommand() {
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

    @Override
    public void undoCommand() {
        roomView.getRoom().getRoomElements().add(roomElement);
        roomView.getRoom().getChildrenList().add(roomElement);
        roomView.getPainterList().add(painter);
        roomView.addElementToTree(roomElement);
        roomElement.notifySubscriber(roomElement);
    }
}
