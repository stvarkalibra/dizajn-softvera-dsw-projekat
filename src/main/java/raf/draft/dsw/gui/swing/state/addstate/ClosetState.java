package raf.draft.dsw.gui.swing.state.addstate;

import raf.draft.dsw.elements.*;
import raf.draft.dsw.gui.swing.painters.BathtubPainter;
import raf.draft.dsw.gui.swing.painters.BedPainter;
import raf.draft.dsw.gui.swing.painters.ClosetPainter;
import raf.draft.dsw.gui.swing.state.State;
import raf.draft.dsw.jtabbedpane.view.RoomView;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;

public class ClosetState implements State{
    private ClosetPainter cp;
    @Override
    public void myMouseClicked(RoomView roomView, Point location) {
        cp=new ClosetPainter(roomView.getRoom().getPendingElement());
        if(!cp.elementAt(roomView.getRoom().getPendingElement(),location)) {
            roomView.getRoom().getRoomElements().add(roomView.getRoom().getPendingElement());
            roomView.getRoom().getChildrenList().add(roomView.getRoom().getPendingElement());
            roomView.getPainterList().add(cp);
            roomView.addElementToTree(roomView.getRoom().getPendingElement());
            ClosetState.FrameWork framework = new ClosetState.FrameWork((Closet) roomView.getRoom().getPendingElement(), location, roomView.getK(), roomView);
            roomView.getjPanelRoom().setLayout(new BorderLayout());
            roomView.getjPanelRoom().add(framework, BorderLayout.CENTER);
        }
    }

    private class FrameWork extends JPanel {
        private final Closet closet;
        private final Point location;
        private double k;
        private  RoomView roomView;

        public FrameWork(Closet closet, Point location, double k, RoomView roomView) {
            this.closet = closet;
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
                if(roomElement instanceof  Closet){
                    cp=new ClosetPainter(roomElement);
                    Point elementLocation = roomElement.getLocation();
                    cp.paint(g2d, elementLocation, (int) (roomElement.getDimensionLength() * k), (int) (roomElement.getDimensionWidth() * k));
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
