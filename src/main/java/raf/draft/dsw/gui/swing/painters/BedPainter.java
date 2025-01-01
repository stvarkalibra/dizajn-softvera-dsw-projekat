package raf.draft.dsw.gui.swing.painters;

import raf.draft.dsw.elements.Bed;
import raf.draft.dsw.elements.RoomElement;
import raf.draft.dsw.model.structures.Room;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.geom.GeneralPath;
import java.awt.geom.Rectangle2D;
import java.util.List;

public class BedPainter extends MyPainter {

    public BedPainter(RoomElement element) {
        super(element);
    }

    @Override
    public void paint(Graphics2D g, Point startPoint, int height, int width) {
        AffineTransform originalTransform = g.getTransform();

        int centerX = startPoint.x + width / 2;
        int centerY = startPoint.y + height / 2;
        g.translate(centerX,centerY);
        g.scale(getRoomElement().getScale(), getRoomElement().getScale());
        g.translate(-centerX,-centerY);
        g.rotate(Math.toRadians(getRoomElement().getRotation() * 90), centerX, centerY);
        BasicStroke stroke = new BasicStroke(5f);
        g.setStroke(stroke);

        GeneralPath path = new GeneralPath();
        path.moveTo(startPoint.getX(), startPoint.getY());
        path.lineTo(startPoint.getX(), startPoint.getY() + height);
        path.lineTo(startPoint.getX() + width, startPoint.getY() + height);
        path.lineTo(startPoint.getX() + width, startPoint.getY());
        path.closePath();

        GeneralPath pillowPath = new GeneralPath();
        pillowPath.moveTo(startPoint.getX() + width / 8, startPoint.getY() + height / 10);
        pillowPath.lineTo(startPoint.getX() + 7 * width / 8, startPoint.getY() + height / 10);
        pillowPath.lineTo(startPoint.getX() + 7 * width / 8, startPoint.getY() + 2 * height / 8);
        pillowPath.lineTo(startPoint.getX() + width / 8, startPoint.getY() + 2 * height / 8);
        pillowPath.closePath();

        g.draw(path);
        g.draw(pillowPath);

        g.setTransform(originalTransform);
    }



    public Rectangle2D getMyBounds(RoomElement element) {
        int x = element.getLocation().x;
        int y = element.getLocation().y;
        double k = 0;
        if (element.getParent() instanceof Room) {
            k = ((Room) element.getParent()).getRoomView().getK();
        }
        double height = element.getDimensionLength() * k;
        double width = element.getDimensionWidth() * k;
        element.setBounds(new Rectangle(x,y,(int) width,(int) height));
        return new Rectangle2D.Double(x, y, width, height);
    }

    @Override
    public boolean elementAt(RoomElement element, Point pos) {
        Rectangle2D newBounds = getMyBounds(element);

        if (element.getParent() instanceof Room) {
            System.out.println("STARI: " + ((Room) element.getParent()).getRoomElements().getLast());
            System.out.println("STARI: " + ((Room) element.getParent()).getRoomElements().getFirst());
            for (RoomElement roomElement : ((Room) element.getParent()).getRoomElements()) {

                if (roomElement.getBounds().intersects(newBounds)) {
                    return true;
                }
            }
        }
        return false;
    }
}
