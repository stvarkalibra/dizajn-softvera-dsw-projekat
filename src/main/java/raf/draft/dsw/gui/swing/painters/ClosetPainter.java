package raf.draft.dsw.gui.swing.painters;

import raf.draft.dsw.elements.RoomElement;
import raf.draft.dsw.model.structures.Room;

import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.awt.geom.GeneralPath;
import java.awt.geom.Line2D;
import java.awt.geom.Rectangle2D;

public class ClosetPainter extends MyPainter{
    public ClosetPainter(RoomElement element) {
        super(element);
    }

    @Override
    public void paint(Graphics2D g, Point startPoint, int height, int width) {
        BasicStroke stroke = new BasicStroke(5f);
        g.setStroke(stroke);
        Rectangle2D.Float rectangle2D = new Rectangle2D.Float(startPoint.x, startPoint.y, width, height);
        Line2D.Float line = new Line2D.Float(startPoint.x + width / 2, startPoint.y, startPoint.x + width / 2, startPoint.y + height);
        Ellipse2D.Float ellipse1 = new Ellipse2D.Float(startPoint.x + 3 * width / 10, startPoint.y + height / 2 - width / 20, width / 10, width / 10);
        Ellipse2D.Float ellipse2 = new Ellipse2D.Float(startPoint.x + 6 * width / 10, startPoint.y + height / 2 - width / 20, width / 10, width / 10);
        g.fill(ellipse1);
        g.fill(ellipse2);
        g.draw(rectangle2D);
        g.draw(line);
        g.draw(ellipse1);
        g.draw(ellipse2);
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
        element.setBounds(new Rectangle2D.Double(x,y,width,height));
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
