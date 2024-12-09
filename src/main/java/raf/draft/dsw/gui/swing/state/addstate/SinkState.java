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

public class SinkState implements State {
    @Override
    public void myMouseClicked(RoomView roomView, Point location) {
        roomView.getRoom().getRoomElements().add(roomView.getRoom().getPendingElement());
        roomView.getRoom().getChildrenList().add(roomView.getRoom().getPendingElement());

        SinkState.FrameWork framework = new SinkState.FrameWork((Sink) roomView.getRoom().getPendingElement(), location, roomView.getK());
        roomView.getjPanelRoom().setLayout(new BorderLayout());
        roomView.getjPanelRoom().add(framework, BorderLayout.CENTER);
    }

    private class FrameWork extends JPanel {
        private final Sink sink;
        private final Point location;
        private double k;

        public FrameWork(Sink sink, Point location, double k) {
            this.sink = sink;
            this.location = location;
            this.k=k;
            setOpaque(false);
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            Graphics2D g2d = (Graphics2D) g;
            SinkPainter sp = new SinkPainter(sink);
            sp.paint(g2d, location, (int) (sink.getDimensionLength() * k), (int) (sink.getDimensionWidth() *  k));
        }
    }

    @Override
    public void myMouseScroll() {

    }

    @Override
    public void myMouseReleased() {

    }

    @Override
    public void myMouseDragged() {

    }
    @Override
    public void update(Object o) {

    }
}
