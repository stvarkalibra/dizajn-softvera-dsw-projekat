package raf.draft.dsw.gui.swing.painters;

import raf.draft.dsw.elements.Bed;
import raf.draft.dsw.elements.RoomElement;
import raf.draft.dsw.model.structures.Room;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.geom.GeneralPath;
import java.awt.geom.Rectangle2D;

public class BedPainter extends MyPainter{

    Point startPoint;

    private AffineTransform originalTransform = null;

    public BedPainter(RoomElement element) {
        super(element);
    }

    @Override
    public void paint(Graphics2D g, Point startPoint, int height, int width) {
        BasicStroke stroke = new BasicStroke(5f);
        this.startPoint = new Point(startPoint.x, startPoint.y);
        g.setStroke(stroke);
        GeneralPath path = new GeneralPath();
        path.moveTo(startPoint.getX(), startPoint.getY());
        path.lineTo(startPoint.getX(), startPoint.getY() + height);
        path.lineTo(startPoint.getX() + width, startPoint.getY() + height);
        path.lineTo(startPoint.getX() + width, startPoint.getY());
        path.closePath();
        GeneralPath pillowPath = new GeneralPath();
        pillowPath.moveTo(startPoint.getX() + width / 8, startPoint.getY() + height / 10);
        pillowPath.lineTo(startPoint.getX() + 7 * width / 8, startPoint.getY() + height / 10);
        pillowPath.lineTo(startPoint.getX() + 7 * width / 8, startPoint.getY() + 2 * height / 8);
        pillowPath.lineTo(startPoint.getX() + width / 8, startPoint.getY() + 2 * height / 8);
        pillowPath.closePath();
        g.draw(path);
        g.draw(pillowPath);
        if (originalTransform == null) {
            originalTransform = g.getTransform();
        }
        g.setTransform(originalTransform);
    }

    public void myRotate(Graphics2D g){
        originalTransform = g.getTransform();

        AffineTransform transform = new AffineTransform();
        transform.translate(startPoint.getX(), startPoint.getY());
        transform.rotate(Math.toRadians(getRoomElement().getRotation() * 90));
        g.transform(transform);
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
