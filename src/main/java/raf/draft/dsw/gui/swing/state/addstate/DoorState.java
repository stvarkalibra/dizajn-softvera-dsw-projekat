package raf.draft.dsw.gui.swing.state.addstate;

import raf.draft.dsw.elements.Bed;
import raf.draft.dsw.elements.Door;
import raf.draft.dsw.elements.RoomElement;
import raf.draft.dsw.gui.swing.painters.BedPainter;
import raf.draft.dsw.gui.swing.painters.DoorPainter;
import raf.draft.dsw.gui.swing.state.State;
import raf.draft.dsw.jtabbedpane.view.RoomView;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;

public class DoorState implements State {
    private DoorPainter dp;
    @Override
    public void myMouseClicked(RoomView roomView, Point location) {
        dp=new DoorPainter(roomView.getRoom().getPendingElement());
        if(!dp.elementAt(roomView.getRoom().getPendingElement(),location)) {
            roomView.getRoom().getRoomElements().add(roomView.getRoom().getPendingElement());
            roomView.getRoom().getChildrenList().add(roomView.getRoom().getPendingElement());
            roomView.getPainterList().add(dp);
            roomView.addElementToTree(roomView.getRoom().getPendingElement());
            DoorState.FrameWork framework = new DoorState.FrameWork((Door) roomView.getRoom().getPendingElement(), location, roomView.getK(),roomView);
            roomView.getjPanelRoom().setLayout(new BorderLayout());
            roomView.getjPanelRoom().add(framework, BorderLayout.CENTER);
        }
    }

    private class FrameWork extends JPanel {
        private final Door door;
        private final Point location;
        private double k;
        private RoomView roomView;

        public FrameWork(Door door, Point location, double k,RoomView roomView) {
            this.door = door;
            this.location = location;
            this.k=k;
            this.roomView=roomView;
            setOpaque(false);
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            Graphics2D g2d = (Graphics2D) g;
            for (RoomElement roomElement : roomView.getRoom().getRoomElements()) {
                if(roomElement instanceof Door) {
                    dp=new DoorPainter(roomElement);
                    Point elementLocation = roomElement.getLocation();
                    dp.paint(g2d, elementLocation, (int) (roomElement.getDimensionLength() * k), (int) (roomElement.getDimensionWidth() * k));
                }
            }
        }
    }

    @Override
    public void myMouseScrolled() {

    }

    @Override
    public void myMouseReleased(RoomView roomView) {

    }

    @Override
    public void myMouseDragged(RoomView roomView, MouseEvent mouseEvent) {

    }
    @Override
    public void update(Object o) {

    }
}
