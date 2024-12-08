package raf.draft.dsw.gui.swing.painters;

import raf.draft.dsw.elements.RoomElement;

import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;

public class WashingMaschinePainter extends MyPainter{
    public WashingMaschinePainter(RoomElement element) {
        super(element);
    }

    @Override
    public void paint(Graphics2D g, Point startPoint, int height, int width) {
        BasicStroke stroke = new BasicStroke(5f);
        g.setStroke(stroke);
        Rectangle2D.Float rectangle = new Rectangle2D.Float(startPoint.x, startPoint.y, width, height);
        Ellipse2D.Float ellipse = new Ellipse2D.Float(startPoint.x + width / 4, startPoint.y + height / 4, width / 2, height / 2);
        g.draw(rectangle);
        g.draw(ellipse);
    }

    @Override
    public boolean elementAt(RoomElement element, Point pos) {
        return false;
    }
}
