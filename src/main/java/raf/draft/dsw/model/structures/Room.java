package raf.draft.dsw.model.structures;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import raf.draft.dsw.controller.observer.IPublisher;
import raf.draft.dsw.controller.observer.ISubscriber;
import raf.draft.dsw.gui.swing.MainFrame;
import raf.draft.dsw.jtabbedpane.view.RoomView;
import raf.draft.dsw.elements.RoomElement;
import raf.draft.dsw.model.nodes.DraftNode;
import raf.draft.dsw.model.nodes.DraftNodeComposite;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Room extends DraftNodeComposite implements IPublisher {
    int width=0;
    int height=0;
    private double k=0;
    @JsonIgnore
    private RoomView roomView;
    @JsonIgnore
    private RoomElement pendingElement;
    @JsonIgnore
    private List<ISubscriber> subscriberList=new ArrayList<>();
    private List<RoomElement> roomElements=new ArrayList<>();
    private List<RoomElement> cpyModel = new ArrayList<>();
    @JsonCreator
    public Room(
            @JsonProperty("nodeName") String nodeName,
            @JsonProperty("width") int width,
            @JsonProperty("height") int height,
            @JsonProperty("roomElements") List<RoomElement> roomElements) {
        super(nodeName, null);
        this.width = width;
        this.height = height;
        this.roomElements = roomElements != null ? roomElements : new ArrayList<>();
    }
    public Room(String nodeName, DraftNode parent) {
        super(nodeName, parent);
    }

    public void setWidth(int width) {
        this.width = width;
        setK();
        notifySubscriber(this);
    }

    public void setHeight(int height) {
        this.height = height;
        setK();
        notifySubscriber(this);
    }

    public double getK() {
        return k;
    }

    public void setK() {
        double heightPixel = this.convertCmIntoPixel(this.getHeight());
        double widthPixel = this.convertCmIntoPixel(this.getWidth());
        double aspectRatio = widthPixel / heightPixel;
        double jPanelRoomHeight = MainFrame.getInstance().getjTabbedPaneImplementation().getjTabbedPane().getHeight();
        double jPanelRoomWidth = MainFrame.getInstance().getjTabbedPaneImplementation().getjTabbedPane().getWidth();
        if (aspectRatio > 1) {
            widthPixel = jPanelRoomWidth;
            heightPixel = widthPixel / aspectRatio;
            k = widthPixel / this.getWidth();
        } else {
            heightPixel = jPanelRoomHeight;
            widthPixel = heightPixel * aspectRatio;
            k = heightPixel / this.getHeight();
        }
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
                if(this.getParent() instanceof Project){
                    ((Project) this.getParent()).setChanged(true);
                }else{
                    ((Project)this.getParent().getParent()).setChanged(true);
                }
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
        if(this.getParent() instanceof Project){
            ((Project) this.getParent()).setChanged(true);
        }else{
            ((Project)this.getParent().getParent()).setChanged(true);
        }
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

    public List<RoomElement> getCpyModel() {
        return cpyModel;
    }

}
