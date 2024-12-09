package raf.draft.dsw.gui.swing.state.addstate;

import raf.draft.dsw.elements.Bed;
import raf.draft.dsw.gui.swing.painters.BedPainter;
import raf.draft.dsw.gui.swing.state.State;
import raf.draft.dsw.jtabbedpane.view.RoomView;

import javax.swing.*;
import java.awt.*;

public class BedState implements State {
    private double length_k, width_k = 0;
    @Override
    public void myMouseClicked(RoomView roomView, Point location) {
        roomView.getRoom().getRoomElements().add(roomView.getRoom().getPendingElement());
        roomView.getRoom().getChildrenList().add(roomView.getRoom().getPendingElement());

        length_k = roomView.getK_length();;
        width_k = roomView.getK_width();;
        System.out.println("BedState Length: " + length_k + " Width: " + width_k);

        FrameWork framework = new FrameWork((Bed) roomView.getRoom().getPendingElement(), location);
        roomView.getjPanelRoom().setLayout(new BorderLayout());
        roomView.getjPanelRoom().add(framework, BorderLayout.CENTER);
    }

    private class FrameWork extends JPanel {
        private final Bed bed;
        private final Point location;

        public FrameWork(Bed bed, Point location) {
            this.bed = bed;
            this.location = location;
            setOpaque(false);
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            Graphics2D g2d = (Graphics2D) g;
            BedPainter bp = new BedPainter(bed);
            bp.paint(g2d, location, (int) (bed.getDimensionLength() * length_k), (int) (bed.getDimensionWidth() *  width_k));
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
