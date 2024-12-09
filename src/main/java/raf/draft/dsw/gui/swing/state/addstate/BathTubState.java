package raf.draft.dsw.gui.swing.state.addstate;

import raf.draft.dsw.elements.BathTub;
import raf.draft.dsw.elements.Bed;
import raf.draft.dsw.elements.RoomElement;
import raf.draft.dsw.gui.swing.painters.BathtubPainter;
import raf.draft.dsw.gui.swing.painters.BedPainter;
import raf.draft.dsw.gui.swing.state.State;
import raf.draft.dsw.jtabbedpane.view.RoomView;

import javax.swing.*;
import java.awt.*;
import java.util.Stack;

public class BathTubState implements State{
    @Override
    public void myMouseClicked(RoomView roomView, Point location) {
        roomView.getRoom().getRoomElements().add(roomView.getRoom().getPendingElement());
        roomView.getRoom().getChildrenList().add(roomView.getRoom().getPendingElement());

        BathTubState.FrameWork framework = new BathTubState.FrameWork((BathTub) roomView.getRoom().getPendingElement(), location, roomView.getK());
        roomView.getjPanelRoom().setLayout(new BorderLayout());
        roomView.getjPanelRoom().add(framework, BorderLayout.CENTER);
    }

    private class FrameWork extends JPanel {
        private final BathTub bathTub;
        private final Point location;
        private double k;

        public FrameWork(BathTub bathTub, Point location, double k) {
            this.bathTub = bathTub;
            this.location = location;
            this.k=k;
            setOpaque(false);
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            Graphics2D g2d = (Graphics2D) g;
            BathtubPainter btp = new BathtubPainter(bathTub);
            btp.paint(g2d, location, (int) (bathTub.getDimensionLength() * k), (int) (bathTub.getDimensionWidth() *  k));
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
