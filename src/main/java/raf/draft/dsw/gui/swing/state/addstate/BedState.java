package raf.draft.dsw.gui.swing.state.addstate;

import raf.draft.dsw.commandPattern.AddCommand;
import raf.draft.dsw.controller.actions.stateactions.AddAction;
import raf.draft.dsw.elements.Bed;
import raf.draft.dsw.elements.RoomElement;
import raf.draft.dsw.gui.swing.painters.BedPainter;
import raf.draft.dsw.gui.swing.state.State;
import raf.draft.dsw.jtabbedpane.view.RoomView;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelEvent;
import java.awt.geom.AffineTransform;

public class BedState implements State {
    private BedPainter bp;
    @Override
    public void myMouseClicked(RoomView roomView, Point location) {
        bp=new BedPainter(roomView.getRoom().getPendingElement());
        if (!bp.elementAt(roomView.getRoom().getPendingElement(), location)) {
            roomView.getRoom().getRoomElements().add(roomView.getRoom().getPendingElement());
            roomView.getRoom().getChildrenList().add(roomView.getRoom().getPendingElement());
            roomView.getPainterList().add(bp);
            AddCommand addCommand=new AddCommand(roomView,roomView.getRoom().getPendingElement(),bp);
            roomView.getCommandManager().addCommand(addCommand);
            roomView.addElementToTree(roomView.getRoom().getPendingElement());
            BedState.FrameWork framework = new BedState.FrameWork((Bed) roomView.getRoom().getPendingElement(), location, roomView.getK(),roomView);
            roomView.getjPanelRoom().setLayout(new BorderLayout());
            roomView.getjPanelRoom().add(framework, BorderLayout.CENTER);
        }
    }

    private class FrameWork extends JPanel {
        private final Bed bed;
        private final Point location;
        private double k;
        private RoomView roomView;

        public FrameWork(Bed bed, Point location, double k,RoomView roomView) {
            this.bed = bed;
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
                if (roomElement instanceof Bed) {
                    bp=new BedPainter(roomElement);
                    Point elementLocation = roomElement.getLocation();
                    bp.paint(g2d, elementLocation, (int) (roomElement.getDimensionLength() * k), (int) (roomElement.getDimensionWidth() * k));
                }
            }
        }
    }

    @Override
    public void myMouseScrolled(RoomView roomView, MouseWheelEvent event) {

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
