package raf.draft.dsw.gui.swing.painters;

import raf.draft.dsw.elements.RoomElement;

import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.awt.geom.GeneralPath;

public class SinkPainter extends MyPainter{
    public SinkPainter(RoomElement element) {
        super(element);
    }

    @Override
    public void paint(Graphics2D g, Point startPoint, int height, int width) {
        BasicStroke stroke = new BasicStroke(5f);
        g.setStroke(stroke);
        GeneralPath path = new GeneralPath();
        path.moveTo(startPoint.x, startPoint.y + height);
        path.lineTo(startPoint.x + width, startPoint.y + height);
        path.lineTo(startPoint.x + width / 2, startPoint.y);
        path.closePath();
        Ellipse2D.Float ellipse = new Ellipse2D.Float(startPoint.x + 2 * width / 5, startPoint.y +  height / 2, width / 5, width / 5);
        g.fill(ellipse);
        g.draw(path);
        g.draw(ellipse);
    }

    @Override
    public boolean elementAt(RoomElement element, Point pos) {
        return false;
    }
}
