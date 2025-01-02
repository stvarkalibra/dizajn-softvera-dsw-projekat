package raf.draft.dsw.model.nodes;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import raf.draft.dsw.elements.*;
import raf.draft.dsw.model.structures.Building;
import raf.draft.dsw.model.structures.Project;
import raf.draft.dsw.model.structures.Room;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
@JsonTypeInfo(
        use = JsonTypeInfo.Id.NAME,
        include = JsonTypeInfo.As.PROPERTY,
        property = "_type"
)
@JsonSubTypes({
        @JsonSubTypes.Type(value = Project.class, name = "Project"),
        @JsonSubTypes.Type(value = Building.class, name = "Building"),
        @JsonSubTypes.Type(value = Room.class, name = "Room"),
        @JsonSubTypes.Type(value = Bed.class, name = "Bed"),
        @JsonSubTypes.Type(value = WashingMachine.class, name = "WashingMachine"),
        @JsonSubTypes.Type(value = Sink.class, name = "Sink"),
        @JsonSubTypes.Type(value = ToiletSeat.class, name = "Toilet"),
        @JsonSubTypes.Type(value = Boiler.class, name = "Boiler"),
        @JsonSubTypes.Type(value = Closet.class, name = "Closet"),
        @JsonSubTypes.Type(value = BathTub.class, name = "BathTub"),
        @JsonSubTypes.Type(value = Door.class, name = "Door"),
        @JsonSubTypes.Type(value = Table.class, name = "Table"),
})
public abstract class DraftNode {
    private String nodeName;
    @JsonIgnore
    private DraftNode parent;

    public DraftNode(String nodeName, DraftNode parent) {
        this.nodeName = nodeName;
        this.parent = parent;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        DraftNode draftNode = (DraftNode) object;
        return Objects.equals(nodeName, draftNode.nodeName) && Objects.equals(parent, draftNode.parent);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nodeName, parent);
    }

    public String getNodeName() {
        return nodeName;
    }

    //----------------------

    private final List<Runnable> listeners = new ArrayList<>();

    public void addListener(Runnable listener) {
        listeners.add(listener);
    }

    private void notifyListeners() {
        for (Runnable listener : listeners) {
            listener.run();
        }
    }

    public void setNodeName(String nodeName) {
        this.nodeName = nodeName;
        notifyListeners();
    }

    //----------------------
    @JsonIgnore
    public DraftNode getParent() {
        return parent;
    }

    public void setParent(DraftNode parent) {
        this.parent = parent;
    }
}
