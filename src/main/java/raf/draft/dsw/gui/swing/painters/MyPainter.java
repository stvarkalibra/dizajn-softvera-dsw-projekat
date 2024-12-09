package raf.draft.dsw.gui.swing.painters;

import raf.draft.dsw.elements.RoomElement;

import java.awt.*;

public abstract class MyPainter {
    public MyPainter(RoomElement element) {}

    public abstract void paint(Graphics2D g, Point startPoint, int height, int width);

    public abstract boolean elementAt(RoomElement element, Point pos);
}