package raf.draft.dsw.gui.swing.state.addstate;

import raf.draft.dsw.elements.Bed;
import raf.draft.dsw.elements.RoomElement;
import raf.draft.dsw.elements.ToiletSeat;
import raf.draft.dsw.gui.swing.painters.BedPainter;
import raf.draft.dsw.gui.swing.painters.ToiletPainter;
import raf.draft.dsw.gui.swing.state.State;
import raf.draft.dsw.jtabbedpane.view.RoomView;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;

public class ToiletState implements State {
    private ToiletPainter top;
    @Override
    public void myMouseClicked(RoomView roomView, Point location) {

        top=new ToiletPainter(roomView.getRoom().getPendingElement());
        if(!top.elementAt(roomView.getRoom().getPendingElement(),location)) {
            roomView.getRoom().getRoomElements().add(roomView.getRoom().getPendingElement());
            roomView.getRoom().getChildrenList().add(roomView.getRoom().getPendingElement());
            roomView.getPainterList().add(top);
            ToiletState.FrameWork framework = new ToiletState.FrameWork((ToiletSeat) roomView.getRoom().getPendingElement(), location, roomView.getK(), roomView);
            roomView.getjPanelRoom().setLayout(new BorderLayout());
            roomView.getjPanelRoom().add(framework, BorderLayout.CENTER);
        }
    }

    private class FrameWork extends JPanel {
        private final ToiletSeat toilet;
        private final Point location;
        private double k;
        private RoomView roomView;

        public FrameWork(ToiletSeat toilet, Point location, double k, RoomView roomView) {
            this.toilet = toilet;
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
                if(roomElement instanceof ToiletSeat){
                Point elementLocation = roomElement.getLocation();
                top.paint(g2d, elementLocation, (int) (roomElement.getDimensionLength() * k), (int) (roomElement.getDimensionWidth() * k));
                }
            }
        }
    }

    @Override
    public void myMouseScrolled() {

    }

    @Override
    public void myMouseReleased() {

    }

    @Override
    public void myMouseDragged(RoomView roomView, MouseEvent mouseEvent) {

    }
    @Override
    public void update(Object o) {

    }
}
