package raf.draft.dsw.commandPattern;

import raf.draft.dsw.elements.RoomElement;
import raf.draft.dsw.gui.swing.painters.MyPainter;
import raf.draft.dsw.jtabbedpane.view.RoomView;
import raf.draft.dsw.model.structures.Room;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class CopyPasteCommand extends AbstractCommand{
    private List<RoomElement> roomElements;
    private List<MyPainter> painters;
    private Room room;
    public CopyPasteCommand(RoomView roomView, List<RoomElement> roomElements, List<MyPainter> painters, Room room) {
        super(roomView);
        this.roomElements = new ArrayList<>(roomElements);
        this.painters = new ArrayList<>(painters);
        this.room=room;
    }

    @Override
    public void doCommand() {
        for(int i=0;i<roomElements.size();i++){
            room.addChild(roomElements.get(i));
            room.getRoomView().addElementToTree(roomElements.get(i));
            room.getRoomElements().add(roomElements.get(i));
            room.getChildrenList().add(roomElements.get(i));

            room.getRoomView().getPainterList().add(painters.get(i));
            roomElements.get(i).addSubscriber(room.getRoomView());
            roomElements.get(i).notifySubscriber(roomElements.get(i));
        }
    }

    @Override
    public void undoCommand() {
        for(int i=0;i<roomElements.size();i++) {
            Iterator<RoomElement> roomElementIterator = roomView.getRoom().getRoomElements().iterator();
            while (roomElementIterator.hasNext()) {
                RoomElement roomElement = roomElementIterator.next();
                if (roomElement.equals(this.roomElements.get(i))) {
                    roomElementIterator.remove();

                    roomView.getRoom().getChildrenList().remove(roomElement);
                    Iterator<MyPainter> painterIterator = roomView.getPainterList().iterator();
                    while (painterIterator.hasNext()) {
                        MyPainter painter = painterIterator.next();
                        if (painter.getRoomElement().equals(this.roomElements.get(i))) {
                            painterIterator.remove();
                            break;
                        }
                    }
                }
                roomView.getRoom().notifySubscriber(this.roomElements.get(i));
            }
        }
        roomView.getjPanelRoom().revalidate();
        roomView.getjPanelRoom().repaint();
    }
}
