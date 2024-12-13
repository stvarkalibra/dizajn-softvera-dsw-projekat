package raf.draft.dsw.gui.swing.state.addstate;

import raf.draft.dsw.elements.RoomElement;
import raf.draft.dsw.elements.WashingMachine;
import raf.draft.dsw.gui.swing.painters.WashingMachinePainter;
import raf.draft.dsw.gui.swing.state.State;
import raf.draft.dsw.jtabbedpane.view.RoomView;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;

public class WashingMachineState implements State {
    private WashingMachinePainter wp;
    @Override
    public void myMouseClicked(RoomView roomView, Point location) {
        wp=new WashingMachinePainter(roomView.getRoom().getPendingElement());
        if(!wp.elementAt(roomView.getRoom().getPendingElement(),location)) {
            roomView.getRoom().getRoomElements().add(roomView.getRoom().getPendingElement());
            roomView.getRoom().getChildrenList().add(roomView.getRoom().getPendingElement());
            roomView.getPainterList().add(wp);
            roomView.addElementToTree(roomView.getRoom().getPendingElement());
            WashingMachineState.FrameWork framework = new WashingMachineState.FrameWork((WashingMachine) roomView.getRoom().getPendingElement(), location, roomView.getK(),roomView);
            roomView.getjPanelRoom().setLayout(new BorderLayout());
            roomView.getjPanelRoom().add(framework, BorderLayout.CENTER);
        }
    }

    private class FrameWork extends JPanel {
        private final WashingMachine washingMachine;
        private final Point location;
        private double k;
        private  RoomView roomView;

        public FrameWork(WashingMachine washingMachine, Point location, double k,RoomView roomView) {
            this.washingMachine = washingMachine;
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
                if(roomElement instanceof WashingMachine) {
                    wp=new WashingMachinePainter(roomElement);
                    Point elementLocation = roomElement.getLocation();
                    wp.paint(g2d, elementLocation, (int) (roomElement.getDimensionLength() * k), (int) (roomElement.getDimensionWidth() * k));
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
