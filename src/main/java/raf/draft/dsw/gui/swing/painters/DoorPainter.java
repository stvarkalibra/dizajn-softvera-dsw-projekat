package raf.draft.dsw.gui.swing.painters;

import raf.draft.dsw.elements.RoomElement;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.*;

public class DoorPainter extends MyPainter {


    public DoorPainter(RoomElement element) {
        super(element);
    }


    @Override
    public void paint(Graphics2D g, Point startPoint, int height, int width) {
        BasicStroke stroke = new BasicStroke(5F);
        g.setStroke(stroke);
        Arc2D.Float arc = new Arc2D.Float(startPoint.x , startPoint.y, 2 * width, 2 * height, 90, 90, Arc2D.OPEN);
        Line2D.Float line = new Line2D.Float(startPoint.x + width, startPoint.y, startPoint.x + width, startPoint.y + height);
        g.draw(arc);
        g.draw(line);
    }

    @Override
    public boolean elementAt(RoomElement element, Point pos) {
        return false;
    }
}
