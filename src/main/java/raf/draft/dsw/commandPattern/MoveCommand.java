package raf.draft.dsw.commandPattern;

import raf.draft.dsw.elements.RoomElement;
import raf.draft.dsw.jtabbedpane.view.RoomView;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class MoveCommand extends AbstractCommand{
    private List<Point> initialBounds;
    private List<Point> newLocations;

    public MoveCommand(RoomView roomView, List<Point> initialBounds, List<Point> newLocations) {
        super(roomView);
        this.initialBounds = initialBounds;
        this.newLocations = newLocations;
    }

    @Override
    public void doCommand() {
        for(RoomElement roomElement:roomView.getSelectedElements()){
            roomElement.setLocation(newLocations.get(roomView.getSelectedElements().indexOf(roomElement)));
            roomElement.setBounds(new Rectangle(roomElement.getLocation().x,roomElement.getLocation().y,roomElement.getDimensionWidth(),roomElement.getDimensionLength()));
        }
        roomView.getjPanelRoom().revalidate();
        roomView.getjPanelRoom().repaint();
    }
    @Override
    public void undoCommand() {
        for(RoomElement roomElement:roomView.getSelectedElements()){
            roomElement.setLocation(initialBounds.get(roomView.getSelectedElements().indexOf(roomElement)));
            roomElement.setBounds(new Rectangle(roomElement.getLocation().x,roomElement.getLocation().y,roomElement.getDimensionWidth(),roomElement.getDimensionLength()));
        }
        roomView.getjPanelRoom().revalidate();
        roomView.getjPanelRoom().repaint();
    }
}
