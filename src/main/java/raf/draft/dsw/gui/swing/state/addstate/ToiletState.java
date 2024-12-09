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

public class ToiletState implements State {
    @Override
    public void myMouseClicked(RoomView roomView, Point location) {
        roomView.getRoom().getRoomElements().add(roomView.getRoom().getPendingElement());
        roomView.getRoom().getChildrenList().add(roomView.getRoom().getPendingElement());

        ToiletState.FrameWork framework = new ToiletState.FrameWork((ToiletSeat) roomView.getRoom().getPendingElement(), location, roomView.getK());
        roomView.getjPanelRoom().setLayout(new BorderLayout());
        roomView.getjPanelRoom().add(framework, BorderLayout.CENTER);
    }

    private class FrameWork extends JPanel {
        private final ToiletSeat toilet;
        private final Point location;
        private double k;

        public FrameWork(ToiletSeat toilet, Point location, double k) {
            this.toilet = toilet;
            this.location = location;
            this.k=k;
            setOpaque(false);
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            Graphics2D g2d = (Graphics2D) g;
            ToiletPainter tp = new ToiletPainter(toilet);
            tp.paint(g2d, location, (int) (toilet.getDimensionLength() * k), (int) (toilet.getDimensionWidth() *  k));
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
