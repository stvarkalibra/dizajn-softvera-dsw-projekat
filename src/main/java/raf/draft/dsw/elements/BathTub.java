package raf.draft.dsw.elements;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import raf.draft.dsw.controller.observer.IPublisher;
import raf.draft.dsw.controller.observer.ISubscriber;
import raf.draft.dsw.model.nodes.DraftNode;

import java.awt.*;

public class BathTub extends RoomElement{
    private static int number=0;
    @JsonCreator
    public BathTub(
            @JsonProperty("nodeName") String nodeName,
            @JsonProperty("location") Point location,
            @JsonProperty("dimensionWidth") int dimensionWidth,
            @JsonProperty("dimensionLength") int dimensionLength,
            @JsonProperty("rotation") int rotation) {

        super(nodeName, null, location, dimensionWidth, dimensionLength, rotation);
    }
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
