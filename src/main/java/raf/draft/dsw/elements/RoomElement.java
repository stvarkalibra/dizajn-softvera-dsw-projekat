package raf.draft.dsw.elements;

import raf.draft.dsw.controller.observer.IPublisher;
import raf.draft.dsw.controller.observer.ISubscriber;
import raf.draft.dsw.model.nodes.DraftNode;

import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import java.util.List;

public abstract class RoomElement extends DraftNode implements Prototype, IPublisher {
    private Point location;
    private int dimensionWidth;
    private int dimensionLenght;
    private int rotation;
    private List<ISubscriber> subscriberList=new ArrayList<>();
    private Rectangle2D.Double bounds;

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
    }

    public int getDimensionWidth() {
        return dimensionWidth;
    }

    public void setDimensionWidth(int dimensionWidth) {
        this.dimensionWidth = dimensionWidth;
    }

    public int getDimensionLength() {
        return dimensionLenght;
    }

    public void setDimensionLength(int dimensionLenght) {
        this.dimensionLenght = dimensionLenght;
    }

    public int getRotation() {
        return rotation;
    }

    public void setRotation(int rotation) {
        if (rotation < 0){
            rotation = 3;
        }
        else if (rotation > 3){
            rotation = 0;
        }
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

    public int getDimensionLenght() {
        return dimensionLenght;
    }

    public void setDimensionLenght(int dimensionLenght) {
        this.dimensionLenght = dimensionLenght;
    }

    public List<ISubscriber> getSubscriberList() {
        return subscriberList;
    }

    public void setSubscriberList(List<ISubscriber> subscriberList) {
        this.subscriberList = subscriberList;
    }

    public Rectangle2D.Double getBounds() {
        return bounds;
    }

    public void setBounds(Rectangle2D.Double bounds) {
        this.bounds = bounds;
    }
}
