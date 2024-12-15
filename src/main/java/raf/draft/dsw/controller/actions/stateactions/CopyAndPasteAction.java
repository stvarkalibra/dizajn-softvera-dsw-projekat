package raf.draft.dsw.controller.actions.stateactions;

import raf.draft.dsw.controller.actions.AbstractRoomAction;
import raf.draft.dsw.elements.*;
import raf.draft.dsw.gui.swing.MainFrame;
import raf.draft.dsw.gui.swing.painters.*;
import raf.draft.dsw.model.structures.Room;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class CopyAndPasteAction extends AbstractRoomAction {
    public CopyAndPasteAction() {
        putValue(SMALL_ICON, loadIcon("/images/copy.png"));
        putValue(NAME, "Copy&Paste");
        putValue(SHORT_DESCRIPTION, "Copy and paste element");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        int index= MainFrame.getInstance().getjTabbedPaneImplementation().getjTabbedPane().getSelectedIndex();
        Object o=MainFrame.getInstance().getjTabbedPaneImplementation().getRoomList().get(index);
        if(o instanceof Room) {
            Room room = (Room)o;
            Boolean hasCollision = false;

            room.getCpyModel().clear();
            for (MyPainter painter : room.getRoomView().getPainterList()){
                for (RoomElement element : room.getRoomView().getSelectedElements()){
                    if (painter.getRoomElement().equals(element)) {
                        room.getCpyModel().add(painter.getRoomElement());
                    }
                }
            }
            for (RoomElement element : room.getCpyModel()){
                RoomElement newElement = null;
                MyPainter newPainter = null;
                double k =  room.getRoomView().getK();
                Point newPoint = new Point(element.getLocation().x + (int) (11 * element.getDimensionWidth() / 10 * k), element.getLocation().y + (int) (11 * element.getDimensionLenght() / 10 * k));

                if (element instanceof Bed){
                    newElement = (Bed) element.clone();
                    newElement.setLocation(newPoint);
                    newPainter = new BedPainter(newElement);
                } else if (element instanceof BathTub){
                    newElement = (BathTub) element.clone();
                    newElement.setLocation(newPoint);
                    newPainter = new BathtubPainter(newElement);
                } else if (element instanceof Boiler){
                    newElement = (Boiler) element.clone();
                    newElement.setLocation(newPoint);
                    newPainter = new BoilerPainter(newElement);
                } else if (element instanceof Closet){
                    newElement = (Closet) element.clone();
                    newElement.setLocation(newPoint);
                    newPainter = new ClosetPainter(newElement);
                } else if (element instanceof Door){
                    newElement = (Door) element.clone();
                    newElement.setLocation(newPoint);
                    newPainter = new DoorPainter(newElement);
                } else if (element instanceof Sink){
                    newElement = (Sink) element.clone();
                    newElement.setLocation(newPoint);
                    newPainter = new SinkPainter(newElement);
                }  else if (element instanceof Table){
                    newElement = (Table) element.clone();
                    newElement.setLocation(newPoint);
                    newPainter = new TablePainter(newElement);
                } else if (element instanceof ToiletSeat){
                    newElement = (ToiletSeat) element.clone();
                    newElement.setLocation(newPoint);
                    newPainter = new ToiletPainter(newElement);
                } else if (element instanceof WashingMachine){
                    newElement = (WashingMachine) element.clone();
                    newElement.setLocation(newPoint);
                    newPainter = new WashingMachinePainter(newElement);
                }


                for (RoomElement otherElement : room.getRoomElements()) {
                    if (newElement == otherElement) continue;
                    if (newElement.getBounds().intersects(otherElement.getBounds())) {
                        hasCollision = true;
                        break;
                    }
                }

                if (hasCollision) {
                    JOptionPane.showMessageDialog(
                            null,
                            "2_ELEMENTS_ARE_INTERSECTING",
                            "Error",
                            JOptionPane.ERROR_MESSAGE
                    );
                    break;
                } else{
                    newElement.setNodeName("Cloned_"+element.getNodeName());
                    room.addChild(newElement);
                    room.getRoomView().addElementToTree(newElement);
                    room.getRoomElements().add(newElement);
                    room.getChildrenList().add(newElement);
                    System.out.println(newElement);

                    room.getRoomView().getPainterList().add(newPainter);
                    for (MyPainter painter : room.getRoomView().getPainterList()){
                        System.out.println(painter);
                    }
                    newElement.addSubscriber(room.getRoomView());
                    newElement.notifySubscriber(newElement);
                }
            }
        }
    }
}
