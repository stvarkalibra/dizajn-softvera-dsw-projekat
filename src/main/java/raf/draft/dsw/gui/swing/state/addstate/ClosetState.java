package raf.draft.dsw.gui.swing.state.addstate;

import raf.draft.dsw.elements.*;
import raf.draft.dsw.gui.swing.painters.BathtubPainter;
import raf.draft.dsw.gui.swing.painters.BedPainter;
import raf.draft.dsw.gui.swing.painters.ClosetPainter;
import raf.draft.dsw.gui.swing.state.State;
import raf.draft.dsw.jtabbedpane.view.RoomView;

import javax.swing.*;
import java.awt.*;

public class ClosetState implements State{
    @Override
    public void myMouseClicked(RoomView roomView, Point location) {
        roomView.getRoom().getRoomElements().add(roomView.getRoom().getPendingElement());
        roomView.getRoom().getChildrenList().add(roomView.getRoom().getPendingElement());

        ClosetState.FrameWork framework = new ClosetState.FrameWork((Closet) roomView.getRoom().getPendingElement(), location, roomView.getK());
        roomView.getjPanelRoom().setLayout(new BorderLayout());
        roomView.getjPanelRoom().add(framework, BorderLayout.CENTER);
    }

    private class FrameWork extends JPanel {
        private final Closet closet;
        private final Point location;
        private double k;

        public FrameWork(Closet closet, Point location, double k) {
            this.closet = closet;
            this.location = location;
            this.k=k;
            setOpaque(false);
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            Graphics2D g2d = (Graphics2D) g;
            ClosetPainter cp = new ClosetPainter(closet);
            cp.paint(g2d, location, (int) (closet.getDimensionLength() * k), (int) (closet.getDimensionWidth() *  k));
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
