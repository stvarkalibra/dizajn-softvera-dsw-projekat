package raf.draft.dsw.elements;

import raf.draft.dsw.controller.observer.IPublisher;
import raf.draft.dsw.controller.observer.ISubscriber;
import raf.draft.dsw.jtabbedpane.view.RoomView;
import raf.draft.dsw.model.nodes.DraftNode;
import raf.draft.dsw.model.structures.Room;

import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import java.util.List;

public abstract class RoomElement extends DraftNode implements Prototype, IPublisher {
    private Point location;
    private int dimensionWidth;
    private int dimensionLenght;
    private int rotation;
    private boolean rotatable = false;
    private List<ISubscriber> subscriberList=new ArrayList<>();
    private Rectangle bounds;

    public RoomElement(String nodeName, DraftNode parent, Point location, int dimensionWidth, int dimensionLenght, int rotation) {
        super(nodeName, parent);
        this.location = location;
        this.dimensionWidth = dimensionWidth;
        this.dimensionLenght = dimensionLenght;
        this.rotation = rotation%4;
    }

    @Override
    public String toString() {
        return "Name: " + getNodeName() +
                ", location= " + location +
                ", dimensionWidth= " + dimensionWidth +
                ", dimensionLenght= " + dimensionLenght +
                ", rotation= " + rotation +
                '}';
    }

    public Point getLocation() {
        return location;
    }

    public void setLocation(Point location) {
        this.location = location;
        double k=0;
        if(this.getParent() instanceof Room){
            k=((Room) this.getParent()).getRoomView().getK();
        }
        setBounds(new Rectangle(this.location.x,this.location.y,dimensionWidth,dimensionLenght));
        updateBounds(k); // Ensure bounds are updated whenever the location changes
    }

    private void updateBounds(double k) {
        this.bounds = new Rectangle(location.x, location.y, (int)(dimensionWidth*k), (int)(dimensionLenght*k));
    }

    public int getDimensionWidth() {
        return dimensionWidth;
    }

    public void setDimensionWidth(int dimensionWidth, double k) {
        this.dimensionWidth = dimensionWidth;
        updateBounds(k);
    }

    public int getDimensionLength() {
        return dimensionLenght;
    }

    public void setDimensionLength(int dimensionLenght,double k) {
        this.dimensionLenght = dimensionLenght;
        updateBounds(k);
    }

    public int getRotation() {
        return rotation;
    }

    public void setRotation(int rotation) {
        if (rotation < 0){
            rotation = 3;
        }
        else if (rotation == 4){
            rotation = 0;
        }else if(rotation>4){
            rotation=rotation%4;
        }
        rotatable = true;
        this.rotation = rotation;
        this.notifySubscriber(this);
    }

    @Override
    public Prototype clone(){
        return null;
    }
    @Override
    public void addSubscriber(ISubscriber s) {
        if(!subscriberList.contains(s)){
            subscriberList.add(s);
        }
    }

    @Override
    public void removeSubscriber(ISubscriber s) {
        if(subscriberList.contains(s)){
            subscriberList.remove(s);
        }
    }

    @Override
    public void notifySubscriber(Object o) {
        for(ISubscriber subscriber: subscriberList){
            subscriber.update(o);
        }
    }

    public List<ISubscriber> getSubscriberList() {
        return subscriberList;
    }

    public void setSubscriberList(List<ISubscriber> subscriberList) {
        this.subscriberList = subscriberList;
    }

    public Rectangle getBounds() {
        return bounds;
    }

    public void setBounds(Rectangle bounds) {
        this.bounds = bounds;
    }

    public boolean isRotatable() {
        return rotatable;
    }
}
