package raf.draft.dsw.gui.swing.state.addstate;

import raf.draft.dsw.elements.Bed;
import raf.draft.dsw.elements.RoomElement;
import raf.draft.dsw.elements.Sink;
import raf.draft.dsw.gui.swing.painters.BedPainter;
import raf.draft.dsw.gui.swing.painters.SinkPainter;
import raf.draft.dsw.gui.swing.state.State;
import raf.draft.dsw.jtabbedpane.view.RoomView;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;

public class SinkState implements State {
    private SinkPainter sp;
    @Override
    public void myMouseClicked(RoomView roomView, Point location) {

        sp=new SinkPainter(roomView.getRoom().getPendingElement());
        if(!sp.elementAt(roomView.getRoom().getPendingElement(),location)) {
            roomView.getRoom().getRoomElements().add(roomView.getRoom().getPendingElement());
            roomView.getRoom().getChildrenList().add(roomView.getRoom().getPendingElement());
            roomView.getPainterList().add(sp);
            SinkState.FrameWork framework = new SinkState.FrameWork((Sink) roomView.getRoom().getPendingElement(), location, roomView.getK(), roomView);
            roomView.getjPanelRoom().setLayout(new BorderLayout());
            roomView.getjPanelRoom().add(framework, BorderLayout.CENTER);
        }
    }

    private class FrameWork extends JPanel {
        private final Sink sink;
        private final Point location;
        private double k;
        private RoomView roomView;

        public FrameWork(Sink sink, Point location, double k, RoomView roomView) {
            this.sink = sink;
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
                if(roomElement instanceof Sink){
                Point elementLocation = roomElement.getLocation();
                sp.paint(g2d, elementLocation, (int) (roomElement.getDimensionLength() * k), (int) (roomElement.getDimensionWidth() * k));
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
