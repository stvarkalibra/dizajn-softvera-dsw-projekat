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
import java.awt.event.MouseEvent;

public class BoilerState implements State{
    private BoilerPainter bop;
    @Override
    public void myMouseClicked(RoomView roomView, Point location) {

        bop=new BoilerPainter(roomView.getRoom().getPendingElement());
        if(!bop.elementAt(roomView.getRoom().getPendingElement(),location)){
            roomView.getRoom().getRoomElements().add(roomView.getRoom().getPendingElement());
            roomView.getRoom().getChildrenList().add(roomView.getRoom().getPendingElement());
            roomView.getPainterList().add(bop);
            BoilerState.FrameWork framework = new BoilerState.FrameWork((Boiler) roomView.getRoom().getPendingElement(), location, roomView.getK(),roomView);
            roomView.getjPanelRoom().setLayout(new BorderLayout());
            roomView.getjPanelRoom().add(framework, BorderLayout.CENTER);
        }

    }

    private class FrameWork extends JPanel {
        private final Boiler boiler;
        private final Point location;
        private double k;
        private RoomView roomView;

        public FrameWork(Boiler boiler, Point location, double k, RoomView roomView) {
            this.boiler = boiler;
            this.location = location;
            this.k=k;
            this.roomView=roomView;
            setOpaque(false);
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            Graphics2D g2d = (Graphics2D) g;
            BoilerPainter bop = new BoilerPainter(boiler);
            for (RoomElement roomElement : roomView.getRoom().getRoomElements()) {
                if(roomElement instanceof Boiler) {
                    bop=new BoilerPainter(roomElement);
                    Point elementLocation = roomElement.getLocation();
                    bop.paint(g2d, elementLocation, (int) (roomElement.getDimensionLength() * k), (int) (roomElement.getDimensionWidth() * k));
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
