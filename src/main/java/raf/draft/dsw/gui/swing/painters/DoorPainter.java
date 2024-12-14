package raf.draft.dsw.gui.swing.painters;

import raf.draft.dsw.elements.RoomElement;
import raf.draft.dsw.model.structures.Room;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.*;

public class DoorPainter extends MyPainter {


    public DoorPainter(RoomElement element) {
        super(element);
    }


    @Override
    public void paint(Graphics2D g, Point startPoint, int height, int width) {
        AffineTransform originalTransform = g.getTransform();
        AffineTransform affineTransform = g.getTransform();

        int centerX = startPoint.x + width / 2;
        int centerY = startPoint.y + height / 2;
        g.scale(getRoomElement().getScale(), getRoomElement().getScale());
        g.rotate(Math.toRadians(getRoomElement().getRotation() * 90), centerX, centerY);
        g.transform(affineTransform);

        BasicStroke stroke = new BasicStroke(5F);
        g.setStroke(stroke);
        Arc2D.Float arc = new Arc2D.Float(startPoint.x , startPoint.y, 2 * width, 2 * height, 90, 90, Arc2D.OPEN);
        Line2D.Float line = new Line2D.Float(startPoint.x + width, startPoint.y, startPoint.x + width, startPoint.y + height);
        g.draw(arc);
        g.draw(line);

        g.setTransform(originalTransform);
    }
    public Rectangle2D getMyBounds(RoomElement element){
        int x=element.getLocation().x;
        int y=element.getLocation().y;
        double k=0;
        if(element.getParent() instanceof Room){
            k=((Room) element.getParent()).getRoomView().getK();
        }
        double height=element.getDimensionLength()*k;
        double width=element.getDimensionWidth()*k;
        element.setBounds(new Rectangle(x,y,(int) width,(int) height));
        return new Rectangle2D.Double(x,y,width,height);
    }
    @Override
    public boolean elementAt(RoomElement element, Point pos) {
        Rectangle2D newBounds=getMyBounds(element);
        System.out.println(newBounds);
        if(element.getParent() instanceof Room){
            for(RoomElement roomElement:((Room) element.getParent()).getRoomElements()){
                System.out.println(roomElement.getBounds());
                if(roomElement.getBounds().intersects(newBounds)){
                    return true;
                }
            }
        }
        return false;
    }
}
