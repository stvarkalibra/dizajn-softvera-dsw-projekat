package raf.draft.dsw.model.structures;

import raf.draft.dsw.controller.observer.IPublisher;
import raf.draft.dsw.controller.observer.ISubscriber;
import raf.draft.dsw.jtabbedpane.view.RoomView;
import raf.draft.dsw.elements.RoomElement;
import raf.draft.dsw.model.nodes.DraftNode;
import raf.draft.dsw.model.nodes.DraftNodeComposite;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Room extends DraftNodeComposite implements IPublisher {
    int width;
    int height;
    private RoomView roomView;
    private RoomElement pendingElement;
    private List<ISubscriber> subscriberList=new ArrayList<>();
    private List<RoomElement> roomElements=new ArrayList<>();
    public Room(String nodeName, DraftNode parent) {
        super(nodeName, parent);
    }

    public void setWidth(int width) {
        this.width = width;
        notifySubscriber(this);
    }

    public void setHeight(int height) {
        this.height = height;
        notifySubscriber(this);
    }

    public void setSubscriberList(List<ISubscriber> subscriberList) {
        this.subscriberList = subscriberList;
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
        for(ISubscriber s:subscriberList){
            if(s!=null) {
                s.update(o);
            }
        }
    }

    public double convertCmIntoPixel(int n){
        final double inch=2.54;
        int dpi= Toolkit.getDefaultToolkit().getScreenResolution();
        return n/inch*dpi;
    }

    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
    }

    public RoomElement getPendingElement() {
        return pendingElement;
    }

    public void setPendingElement(RoomElement pendingElement) {
        this.pendingElement = pendingElement;
    }

    public RoomView getRoomView() {
        return roomView;
    }

    public void setRoomView(RoomView roomView) {
        this.roomView = roomView;
    }

    public List<RoomElement> getRoomElements() {
        return roomElements;
    }

    public void setRoomElements(List<RoomElement> roomElements) {
        this.roomElements = roomElements;
    }
}
