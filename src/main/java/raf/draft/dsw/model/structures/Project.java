package raf.draft.dsw.model.structures;

import raf.draft.dsw.controller.observer.IPublisher;
import raf.draft.dsw.controller.observer.ISubscriber;
import raf.draft.dsw.model.nodes.DraftNode;
import raf.draft.dsw.model.nodes.DraftNodeComposite;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Project extends DraftNodeComposite implements IPublisher {
    private List<ISubscriber> subscriberList=new ArrayList<>();
    private String projectName;
    private String projectAutor;
    private String pathToFolder;
    private Random rand=new Random();
    private float r=rand.nextFloat();
    private float g=rand.nextFloat();
    private float b=rand.nextFloat();
    private Color color=new Color(r,g,b);
    public Project(String nodeName, DraftNode parent, String projectName, String projectAutor, String pathToFolder) {
        super(nodeName, parent);
        this.projectName = projectName;
        this.projectAutor = projectAutor;
        this.pathToFolder = pathToFolder;
    }

    @Override
    public void addChild(DraftNode node) {
        if (node != null && node instanceof Building){
            Building building = (Building) node;
            if (!this.getChildrenList().contains(building)){
                this.getChildrenList().add(building);
            }
        }
        else if(node != null && node instanceof Room){
            Room room = (Room) node;
            if (!this.getChildrenList().contains(room)){
                this.getChildrenList().add(room);
            }
        }
    }

    @Override
    public void removeChild(DraftNode node) {
        if (node instanceof Building){
            Building building = (Building) node;
            if (this.getChildrenList().contains(building)){
                this.getChildrenList().remove(building);
            }
        }
        else if(node instanceof Room){
            Room room = (Room) node;
            if (this.getChildrenList().contains(room)){
                this.getChildrenList().remove(room);
            }
        }
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
        notifySubscriber(this);
    }

    public String getProjectAutor() {
        return projectAutor;
    }

    public void setProjectAutor(String projectAutor) {
        this.projectAutor = projectAutor;
        notifySubscriber(this);
    }

    public String getPathToFolder() {
        return pathToFolder;
    }

    public void setPathToFolder(String pathToFolder) {
        this.pathToFolder = pathToFolder;
        notifySubscriber(this);
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
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
            s.update(o);
        }
    }
}
