package raf.draft.dsw.elements;

import raf.draft.dsw.controller.observer.IPublisher;
import raf.draft.dsw.controller.observer.ISubscriber;
import raf.draft.dsw.model.nodes.DraftNode;

import java.awt.*;

public class BathTub extends RoomElement{
    private static int number=0;
    public BathTub(String nodeName, DraftNode parent, Point location, int dimensionWidth, int dimensionLenght, int rotation) {
        super(nodeName, parent, location, dimensionWidth, dimensionLenght, rotation);
        number++;
        setNodeName(nodeName + number);
    }

    public static int getNumber() {
        return number;
    }

    public static void setNumber(int number) {
        BathTub.number = number;
    }
}
