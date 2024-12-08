package raf.draft.dsw.elements.contoller;

import raf.draft.dsw.elements.RoomElement;
import raf.draft.dsw.model.structures.Room;

import java.awt.*;

public class ElementsController {
    private int lenght;
    private int widht;
    public void dimensions(RoomElement roomElement){
        roomElement.setDimensionWidth(widht);
        roomElement.setDimensionLenght(lenght);
    }
    public void location(RoomElement roomElement,Point location){
        roomElement.setLocation(location);
    }

    public int getLenght() {
        return lenght;
    }

    public void setLenght(int lenght) {
        this.lenght = lenght;
    }

    public int getWidht() {
        return widht;
    }

    public void setWidht(int widht) {
        this.widht = widht;
    }
}
