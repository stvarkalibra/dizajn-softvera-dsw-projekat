package raf.draft.dsw.model.structures;

import raf.draft.dsw.model.nodes.DraftNode;
import raf.draft.dsw.model.nodes.DraftNodeComposite;

import java.awt.*;
import java.util.Random;

public class Building extends DraftNodeComposite {
    Random rand=new Random();
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
}
