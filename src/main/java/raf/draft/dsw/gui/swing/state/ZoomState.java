package raf.draft.dsw.gui.swing.state;

import raf.draft.dsw.elements.RoomElement;
import raf.draft.dsw.jtabbedpane.view.RoomView;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelEvent;

public class ZoomState implements State {
    private double k_scaled = 1.0;
    private static final double ZOOM_STEP = 0.2;

    @Override
    public void myMouseClicked(RoomView roomView, Point location) {
        roomView.getjPanelRoom().addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if(e.getClickCount() == 2) {
                    k_scaled =1.0;
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
            k_scaled += scrollAmount < 0 ? -ZOOM_STEP : ZOOM_STEP;
            if (k_scaled < 0.1) k_scaled = 0.1;

            double scaleFactor = scrollAmount > 0 ? 1 + ZOOM_STEP : 1 - ZOOM_STEP;

            for (RoomElement element : roomView.getRoom().getRoomElements()) {

                int newWidth = (int) (element.getDimensionWidth() * scaleFactor);
                int newHeight = (int) (element.getDimensionLength() * scaleFactor);

                element.setDimensionWidth(newWidth,k_scaled);
                element.setDimensionLength(newHeight,k_scaled);

                // Prilagoditi poziciju elementa tako da se zoomira oko tačke miša
                int newX = (int) ((element.getLocation().getX() - mousePosition.x) * scaleFactor + mousePosition.x);
                int newY = (int) ((element.getLocation().getY() - mousePosition.y) * scaleFactor + mousePosition.y);

                // Ažuriranje pozicije i skaliranja
                element.setLocation(new Point(newX, newY));
                element.setScale(k_scaled); // Postavi skaliranje za element
            }

            // Ponovno iscrtavanje sa novim skaliranjem
            roomView.getjPanelRoom().revalidate();
            roomView.getjPanelRoom().repaint();
        }
    }

    @Override
    public void myMouseReleased(RoomView roomView) {
        // Implementirati kod za kada se otpusti taster miša, ako je potrebno
    }

    @Override
    public void myMouseDragged(RoomView roomView, MouseEvent mouseEvent) {
        // Implementirati kod za pomeranje miša, ako je potrebno
    }

    @Override
    public void update(Object o) {
        // Implementirati metod za obraditi notifikacije, ako je potrebno
    }
}
