package raf.draft.dsw.model.structures;

import com.fasterxml.jackson.annotation.JsonIgnore;
import raf.draft.dsw.controller.observer.IPublisher;
import raf.draft.dsw.controller.observer.ISubscriber;
import raf.draft.dsw.model.nodes.DraftNode;
import raf.draft.dsw.model.nodes.DraftNodeComposite;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Building extends DraftNodeComposite implements IPublisher {
    Random rand=new Random();
    private List<ISubscriber> subscriberList=new ArrayList<>();
    private float r=rand.nextFloat();
    private float g=rand.nextFloat();
    private float b=rand.nextFloat();
    private Color color=new Color(r,g,b);
    public Building(String nodeName, DraftNode parent) {
        super(nodeName, parent);
    }

    @Override
    public void addChild(DraftNode node) {
        if (node instanceof Room){
            Room room = (Room) node;
            if (!this.getChildrenList().contains(room)){
                this.getChildrenList().add(room);
            }
        }
    }

    @Override
    public void removeChild(DraftNode node) {
        if (node instanceof Room){
            Room room = (Room) node;
            if (this.getChildrenList().contains(room)){
                this.getChildrenList().remove(room);
            }
        }
    }
    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

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
            ((Project)this.getParent()).setChanged(true);
            s.update(o);
        }
    }
}
