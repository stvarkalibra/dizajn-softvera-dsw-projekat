package raf.draft.dsw.gui.swing.state.addstate;

import raf.draft.dsw.elements.BathTub;
import raf.draft.dsw.elements.Bed;
import raf.draft.dsw.elements.Boiler;
import raf.draft.dsw.elements.RoomElement;
import raf.draft.dsw.gui.swing.painters.BathtubPainter;
import raf.draft.dsw.gui.swing.painters.BedPainter;
import raf.draft.dsw.gui.swing.painters.BoilerPainter;
import raf.draft.dsw.gui.swing.state.State;
import raf.draft.dsw.jtabbedpane.view.RoomView;

import javax.swing.*;
import java.awt.*;

public class BoilerState implements State{
    @Override
    public void myMouseClicked(RoomView roomView, Point location) {
        roomView.getRoom().getRoomElements().add(roomView.getRoom().getPendingElement());
        roomView.getRoom().getChildrenList().add(roomView.getRoom().getPendingElement());

        BoilerState.FrameWork framework = new BoilerState.FrameWork((Boiler) roomView.getRoom().getPendingElement(), location, roomView.getK());
        roomView.getjPanelRoom().setLayout(new BorderLayout());
        roomView.getjPanelRoom().add(framework, BorderLayout.CENTER);
    }

    private class FrameWork extends JPanel {
        private final Boiler boiler;
        private final Point location;
        private double k;

        public FrameWork(Boiler boiler, Point location, double k) {
            this.boiler = boiler;
            this.location = location;
            this.k=k;
            setOpaque(false);
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            Graphics2D g2d = (Graphics2D) g;
            BoilerPainter bp = new BoilerPainter(boiler);
            bp.paint(g2d, location, (int) (boiler.getDimensionLength() * k), (int) (boiler.getDimensionWidth() *  k));
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
