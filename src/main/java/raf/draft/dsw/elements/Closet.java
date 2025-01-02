package raf.draft.dsw.elements;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import raf.draft.dsw.model.nodes.DraftNode;

import java.awt.*;

public class Closet extends RoomElement{
    private static int number=0;
    @JsonCreator
    public Closet(
            @JsonProperty("nodeName") String nodeName,
            @JsonProperty("location") Point location,
            @JsonProperty("dimensionWidth") int dimensionWidth,
            @JsonProperty("dimensionLength") int dimensionLength,
            @JsonProperty("rotation") int rotation) {

        super(nodeName, null, location, dimensionWidth, dimensionLength, rotation);
    }
    public Closet(String nodeName, DraftNode parent, Point location, int dimensionWidth, int dimensionLenght, int rotation) {
        super(nodeName, parent, location, dimensionWidth, dimensionLenght, rotation);
        number++;
        setNodeName("Closet" + number);
    }
}
