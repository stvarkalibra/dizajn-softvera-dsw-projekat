package raf.draft.dsw.gui.swing.painters;

import raf.draft.dsw.elements.RoomElement;

import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;

public class BoilerPainter extends MyPainter{
    public BoilerPainter(RoomElement element) {
        super(element);
    }

    @Override
    public void paint(Graphics2D g, Point startPoint, int height, int width) {
        BasicStroke stroke = new BasicStroke(5f);
        g.setStroke(stroke);
        Ellipse2D.Float ellipse = new Ellipse2D.Float(startPoint.x, startPoint.y, width, height);
        Line2D.Float line1 = new Line2D.Float(startPoint.x + width / 4, startPoint.y + height / 4, startPoint.x + 3 * width / 4, startPoint.y + 3 * height / 4);
        Line2D.Float line2 = new Line2D.Float(startPoint.x + 3 * width / 4, startPoint.y + height / 4, startPoint.x + width / 4, startPoint.y + 3 * height / 4);
        g.draw(ellipse);
        g.draw(line1);
        g.draw(line2);
    }

    @Override
    public boolean elementAt(RoomElement element, Point pos) {
        return false;
    }
}
