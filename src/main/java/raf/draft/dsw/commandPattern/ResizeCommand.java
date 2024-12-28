package raf.draft.dsw.commandPattern;

import raf.draft.dsw.elements.RoomElement;
import raf.draft.dsw.jtabbedpane.view.RoomView;

import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import java.util.List;

public class ResizeCommand extends AbstractCommand{
    private List<RoomElement> roomElements;
    private List<Integer> oldWidth;
    private List<Integer> oldHeight;
    private List<Integer> newWidth;
    private List<Integer> newHeight;

    public ResizeCommand(RoomView roomView, List<RoomElement> roomElements, List<Integer> oldWidth,List<Integer> oldHeight,List<Integer> newWidth,List<Integer> newHeight) {
        super(roomView);
        this.roomElements=new ArrayList<>(roomElements);
        this.oldWidth=new ArrayList<>(oldWidth);
        this.oldHeight=new ArrayList<>(oldHeight);
        this.newWidth=new ArrayList<>(newWidth);
        this.newHeight=new ArrayList<>(newHeight);
    }

    @Override
    public void doCommand() {
        for(int i=0; i<roomElements.size();i++){
            roomElements.get(i).setDimensionWidth(newWidth.get(i),roomView.getK());
            roomElements.get(i).setDimensionLength(newHeight.get(i),roomView.getK());
            roomView.revalidate();
            roomView.repaint();
        }
    }

    @Override
    public void undoCommand() {
        for(int i=0; i<roomElements.size();i++){
            roomElements.get(i).setDimensionWidth(oldWidth.get(i),roomView.getK());
            roomElements.get(i).setDimensionLength(oldHeight.get(i),roomView.getK());
            roomView.revalidate();
            roomView.repaint();
        }
    }
}
