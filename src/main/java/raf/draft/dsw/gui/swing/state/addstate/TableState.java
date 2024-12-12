package raf.draft.dsw.gui.swing.state.addstate;

import raf.draft.dsw.elements.Bed;
import raf.draft.dsw.elements.RoomElement;
import raf.draft.dsw.elements.Table;
import raf.draft.dsw.elements.contoller.ElementsController;
import raf.draft.dsw.gui.swing.ElementDimensionFrame;
import raf.draft.dsw.gui.swing.painters.BedPainter;
import raf.draft.dsw.gui.swing.painters.TablePainter;
import raf.draft.dsw.gui.swing.state.State;
import raf.draft.dsw.jtabbedpane.view.RoomView;
import raf.draft.dsw.model.nodes.DraftNode;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;

public class TableState implements State {
    private TablePainter tp;
    @Override
    public void myMouseClicked(RoomView roomView, Point location) {
        tp=new TablePainter(roomView.getRoom().getPendingElement());
        if(!tp.elementAt(roomView.getRoom().getPendingElement(),location )) {
            roomView.getRoom().getRoomElements().add(roomView.getRoom().getPendingElement());
            roomView.getRoom().getChildrenList().add(roomView.getRoom().getPendingElement());
            roomView.getPainterList().add(tp);
            TableState.FrameWork framework = new TableState.FrameWork((Table) roomView.getRoom().getPendingElement(), location, roomView.getK(),roomView);
            roomView.getjPanelRoom().setLayout(new BorderLayout());
            roomView.getjPanelRoom().add(framework, BorderLayout.CENTER);
        }
    }

    private class FrameWork extends JPanel {
        private final Table table;
        private final Point location;
        private double k;
        private RoomView roomView;
        public FrameWork(Table table, Point location, double k, RoomView roomView) {
            this.table = table;
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
                if(roomElement instanceof Table) {
                    tp=new TablePainter(roomElement);
                    Point elementLocation = roomElement.getLocation();
                    tp.paint(g2d, elementLocation, (int) (roomElement.getDimensionLength() * k), (int) (roomElement.getDimensionWidth() * k));
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
        if(o instanceof Table){

        }
    }
}
