package raf.draft.dsw.gui.swing.painters;

import raf.draft.dsw.elements.RoomElement;

import java.awt.*;
import java.awt.geom.Arc2D;
import java.awt.geom.GeneralPath;
import java.awt.geom.Rectangle2D;

public class ToiletPainter extends MyPainter{
    public ToiletPainter(RoomElement element) {
        super(element);
    }

    @Override
    public void paint(Graphics2D g, Point startPoint, int height, int width) {
        BasicStroke stroke = new BasicStroke(5f);
        g.setStroke(stroke);
        Rectangle2D.Float rectangle = new Rectangle2D.Float(startPoint.x, startPoint.y, width, height / 4);
        GeneralPath path = new GeneralPath();
        path.moveTo(startPoint.x, startPoint.y + height / 2);
        path.lineTo(startPoint.x, startPoint.y + height / 4);
        path.lineTo(startPoint.x + width, startPoint.y + height / 4);
        path.lineTo(startPoint.x + width, startPoint.y + height / 2);
        Arc2D.Float arc = new Arc2D.Float(startPoint.x, startPoint.y, width, height, 180, 180, Arc2D.OPEN);
        GeneralPath innerPath = new GeneralPath();
        innerPath.moveTo(startPoint.x + width / 4, startPoint.y + height / 2);
        innerPath.lineTo(startPoint.x + width / 4, startPoint.y + 3 * height / 8);
        innerPath.lineTo(startPoint.x + 3 * width / 4, startPoint.y + 3 * height / 8);
        innerPath.lineTo(startPoint.x + 3 * width / 4, startPoint.y + height / 2);
        innerPath.closePath();
        Arc2D.Float arcInner = new Arc2D.Float(startPoint.x + width/ 4, startPoint.y + 4 * height / 13, width / 2, height / 2, 180, 180, Arc2D.PIE);
        
        g.fill(innerPath);
        g.fill(arcInner);
        g.draw(rectangle);
        g.draw(path);
        g.draw(arc);
        g.draw(innerPath);
        g.draw(arcInner);
    }

    @Override
    public boolean elementAt(RoomElement element, Point pos) {
        return false;
    }
}
