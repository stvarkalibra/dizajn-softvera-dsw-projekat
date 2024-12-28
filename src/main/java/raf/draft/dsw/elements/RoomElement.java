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

public abstract class RoomElement extends DraftNode implements Prototype, IPublisher, Cloneable {
    private Point location;
    private int napravljen=0;
    private double scale = 1.0;
    private Point originalLocation;
    private int dimensionWidth=0;
    private int dimensionLenght=0;
    private int originalDimensionWidth;
    private int originalDimensionLenght;
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
        if(napravljen==0){
            this.originalLocation=location;
            napravljen++;
        }
        this.location = location;

        double k=0;
        if(this.getParent() instanceof Room){
            k=((Room) this.getParent()).getRoomView().getK();
        }
        setBounds(new Rectangle(this.location.x,this.location.y,dimensionWidth,dimensionLenght));
        updateBounds(k);
    }

    private void updateBounds(double k) {
        this.bounds = new Rectangle(location.x, location.y, (int)(dimensionWidth*k), (int)(dimensionLenght*k));
    }
    public void resetPosition() {
        location=originalLocation;
    }

    public int getDimensionWidth() {
        return dimensionWidth;
    }

    public void setDimensionWidth(int dimensionWidth, double k) {
        if(this.dimensionWidth==0){
            originalDimensionWidth=dimensionWidth;
        }
        this.dimensionWidth = dimensionWidth;
        updateBounds(k);
    }

    public int getDimensionLength() {
        return dimensionLenght;
    }

    public void setDimensionLength(int dimensionLenght,double k) {
        if(dimensionLenght==0){
            originalDimensionLenght=dimensionLenght;
        }
        this.dimensionLenght = dimensionLenght;
        updateBounds(k);
    }


    public double getScale() {
        return scale;
    }

    public void setScale(double scale) {
        this.scale = scale;
    }

    public int getRotation() {
        return rotation;
    }

    public void setRotation(int rotation) {
        if(this.rotation!=rotation){
            if (rotation < 0){
                rotation = 3;
            } else if (rotation == 4){
                rotation = 0;
            }else if(rotation>4){
                rotation=rotation%4;
            }
            rotatable = true;
            this.rotation = rotation;
            this.notifySubscriber(this);
        }
    }

    @Override
    public Prototype clone(){
        try {
            return (RoomElement) super.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
            return null;
        }
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

    public void setDimensionWidth(int dimensionWidth) {
        this.dimensionWidth = dimensionWidth;
    }

    public int getDimensionLenght() {
        return dimensionLenght;
    }

    public void setDimensionLenght(int dimensionLenght) {
        this.dimensionLenght = dimensionLenght;
    }

    public int getOriginalDimensionWidth() {
        return originalDimensionWidth;
    }

    public void setOriginalDimensionWidth(int originalDimensionWidth) {
        this.originalDimensionWidth = originalDimensionWidth;
    }

    public int getOriginalDimensionLenght() {
        return originalDimensionLenght;
    }

    public void setOriginalDimensionLenght(int originalDimensionLenght) {
        this.originalDimensionLenght = originalDimensionLenght;
    }
}
