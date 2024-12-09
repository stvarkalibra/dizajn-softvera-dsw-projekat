package raf.draft.dsw.gui.swing.state.addstate;

import raf.draft.dsw.elements.Bed;
import raf.draft.dsw.elements.RoomElement;
import raf.draft.dsw.elements.WashingMachine;
import raf.draft.dsw.gui.swing.painters.BedPainter;
import raf.draft.dsw.gui.swing.painters.WashingMaschinePainter;
import raf.draft.dsw.gui.swing.state.State;
import raf.draft.dsw.jtabbedpane.view.RoomView;

import javax.swing.*;
import java.awt.*;

public class WashingMachineState implements State {
    @Override
    public void myMouseClicked(RoomView roomView, Point location) {
        roomView.getRoom().getRoomElements().add(roomView.getRoom().getPendingElement());
        roomView.getRoom().getChildrenList().add(roomView.getRoom().getPendingElement());

        WashingMachineState.FrameWork framework = new WashingMachineState.FrameWork((WashingMachine) roomView.getRoom().getPendingElement(), location, roomView.getK());
        roomView.getjPanelRoom().setLayout(new BorderLayout());
        roomView.getjPanelRoom().add(framework, BorderLayout.CENTER);
    }

    private class FrameWork extends JPanel {
        private final WashingMachine washingMachine;
        private final Point location;
        private double k;

        public FrameWork(WashingMachine washingMachine, Point location, double k) {
            this.washingMachine = washingMachine;
            this.location = location;
            this.k=k;
            setOpaque(false);
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            Graphics2D g2d = (Graphics2D) g;
            WashingMaschinePainter wmp = new WashingMaschinePainter(washingMachine);
            wmp.paint(g2d, location, (int) (washingMachine.getDimensionLength() * k), (int) (washingMachine.getDimensionWidth() *  k));
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
