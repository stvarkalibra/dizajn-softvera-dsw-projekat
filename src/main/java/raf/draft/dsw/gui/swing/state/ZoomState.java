package raf.draft.dsw.gui.swing.state;

import raf.draft.dsw.elements.RoomElement;
import raf.draft.dsw.jtabbedpane.view.RoomView;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelEvent;

public class ZoomState implements State {
    private double k_scaled = 1.0;
    private static final double ZOOM_STEP = 0.1;
    private static final double MIN_SCALE = 0.8;
    private static final double MAX_SCALE = 2.3;

    @Override
    public void myMouseClicked(RoomView roomView, Point location) {

        //OVDE SMO POKUSALI DA NAPRAVIMO DA KAD SE 2 PUTA KLIKNE NA EKRAN DA SE RESTARTUJE ZOOM ALI NAM NIJE USPELO
        //SKILL ISSUE
        roomView.getjPanelRoom().addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2) {
                    k_scaled = 1.0;
                    for (RoomElement element : roomView.getRoom().getRoomElements()) {
                        element.setScale(k_scaled);
                        element.resetPosition();
                        element.setDimensionWidth(element.getOriginalDimensionWidth(), k_scaled);
                        element.setDimensionLength(element.getOriginalDimensionLenght(), k_scaled);

                        element.setBounds(new Rectangle(element.getLocation().x, element.getLocation().y,
                                element.getDimensionWidth(), element.getDimensionLength()));
                    }
                    roomView.getjPanelRoom().revalidate();
                    roomView.getjPanelRoom().repaint();
                }
            }
        });
    }

    @Override
    public void myMouseScrolled(RoomView roomView, MouseWheelEvent e) {
        int scrollAmount = e.getWheelRotation();
        Point mousePosition = e.getPoint();

        if (scrollAmount != 0) {
            double newScale = k_scaled + (scrollAmount > 0 ? -ZOOM_STEP : ZOOM_STEP);
            if (newScale < MIN_SCALE) {
                newScale = MIN_SCALE;
            } else if (newScale > MAX_SCALE) {
                newScale = MAX_SCALE;
            }
            if (newScale == k_scaled) {
                return;
            }

            double scaleFactor = newScale / k_scaled;
            k_scaled = newScale;

            for (RoomElement element : roomView.getRoom().getRoomElements()) {
                int newWidth = (int) (element.getDimensionWidth() * scaleFactor);
                int newHeight = (int) (element.getDimensionLength() * scaleFactor);

                element.setDimensionWidth(newWidth, k_scaled);
                element.setDimensionLength(newHeight, k_scaled);
                int newX = (int) ((element.getLocation().getX() - mousePosition.x) * scaleFactor + mousePosition.x);
                int newY = (int) ((element.getLocation().getY() - mousePosition.y) * scaleFactor + mousePosition.y);

                element.setLocation(new Point(newX, newY));
                element.setScale(k_scaled);
                element.addSubscriber(roomView);
                element.notifySubscriber(element);
            }


        }
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
