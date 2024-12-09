package raf.draft.dsw.gui.swing.painters;

import raf.draft.dsw.elements.RoomElement;

import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;

public class TablePainter extends MyPainter{
    public TablePainter(RoomElement element) {
        super(element);
    }

    @Override
    public void paint(Graphics2D g, Point startPoint, int height, int width) {
        BasicStroke stroke = new BasicStroke(5f);
        g.setStroke(stroke);
        Rectangle2D rectangle2D = new Rectangle2D.Double(startPoint.x, startPoint.y, width, height);
        Ellipse2D.Float ellipse1 = new Ellipse2D.Float(startPoint.x + 2 * width / 10, startPoint.y + 2 * height / 10, width/ 10, width / 10);
        Ellipse2D.Float ellipse2 = new Ellipse2D.Float(startPoint.x + 7 * width / 10, startPoint.y + 2 * height / 10, width / 10, width / 10);
        Ellipse2D.Float ellipse3 = new Ellipse2D.Float(startPoint.x + 2 * width / 10, startPoint.y + 7 * height / 10, width / 10, width / 10);
        Ellipse2D.Float ellipse4 = new Ellipse2D.Float(startPoint.x + 7 * width / 10, startPoint.y + 7 * height / 10, width / 10, width / 10);
        g.fill(ellipse1);
        g.fill(ellipse2);
        g.fill(ellipse3);
        g.fill(ellipse4);
        g.draw(rectangle2D);
        g.draw(ellipse1);
        g.draw(ellipse2);
        g.draw(ellipse3);
        g.draw(ellipse4);
    }

    @Override
    public boolean elementAt(RoomElement element, Point pos) {
        return false;
    }
}
