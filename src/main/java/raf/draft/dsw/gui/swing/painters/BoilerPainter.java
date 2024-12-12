package raf.draft.dsw.gui.swing.painters;

import raf.draft.dsw.elements.RoomElement;
import raf.draft.dsw.model.structures.Room;

import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;
import java.awt.geom.Rectangle2D;

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
