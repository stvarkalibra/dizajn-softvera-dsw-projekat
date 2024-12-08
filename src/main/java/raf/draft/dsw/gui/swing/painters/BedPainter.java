package raf.draft.dsw.gui.swing.painters;

import raf.draft.dsw.elements.RoomElement;

import java.awt.*;
import java.awt.geom.GeneralPath;

public class BedPainter extends MyPainter{

    public BedPainter(RoomElement element) {
        super(element);
    }

    @Override
    public void paint(Graphics2D g, Point startPoint, int height, int width) {
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
    }

    @Override
    public boolean elementAt(RoomElement element, Point pos) {
        return false;
    }
}
