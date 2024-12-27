package raf.draft.dsw.commandPattern;

import raf.draft.dsw.elements.RoomElement;
import raf.draft.dsw.jtabbedpane.view.RoomView;

import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import java.util.List;

public class ResizeCommand extends AbstractCommand{
    private List<Rectangle2D> oldBoundsList=new ArrayList<>();
    private List<RoomElement> elementsList=new ArrayList<>();
    private List<Rectangle2D> newBoundsList=new ArrayList<>();

    public ResizeCommand(RoomView roomView, List<Rectangle2D> oldBoundsList, List<RoomElement> elementsList, List<Rectangle2D> newBoundsList) {
        super(roomView);
        this.oldBoundsList = oldBoundsList;
        this.elementsList = elementsList;
        this.newBoundsList = newBoundsList;
    }
    @Override
    public void doCommand() {
        for(RoomElement roomElement:elementsList){
            roomElement.setBounds((Rectangle) newBoundsList.get(elementsList.indexOf(roomElement)));
            roomElement.setDimensionLength(roomElement.getBounds().height,roomView.getK());
            roomElement.setDimensionWidth(roomElement.getBounds().width,roomView.getK());
            roomElement.notifySubscriber(roomElement);
        }
    }

    @Override
    public void undoCommand() {
        for(RoomElement roomElement:elementsList){
            roomElement.setBounds((Rectangle) oldBoundsList.get(elementsList.indexOf(roomElement)));
            roomElement.setDimensionLength(roomElement.getBounds().height,roomView.getK());
            roomElement.setDimensionWidth(roomElement.getBounds().width,roomView.getK());
            roomView.getjPanelRoom().revalidate();
            roomView.getjPanelRoom().repaint();
        }
    }
}
