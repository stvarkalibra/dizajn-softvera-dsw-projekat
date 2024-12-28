package raf.draft.dsw.commandPattern;

import raf.draft.dsw.elements.RoomElement;
import raf.draft.dsw.jtabbedpane.view.RoomView;

public class EditCommand extends AbstractCommand{
    private int oldLenght;
    private int oldWidth;
    private int oldRotation;
    private int newLenght;
    private int newWidth;
    private int newRotation;
    private RoomElement roomElement;
    public EditCommand(RoomView roomView, int oldLenght, int oldWidth, int oldRotation, int newLenght, int newWidth, int newRotation, RoomElement roomElement) {
        super(roomView);
        this.oldLenght = oldLenght;
        this.oldWidth = oldWidth;
        this.oldRotation = oldRotation;
        this.newLenght = newLenght;
        this.newWidth = newWidth;
        this.newRotation = newRotation;
        this.roomElement=roomElement;
    }

    @Override
    public void doCommand() {
        roomElement.setRotation(newRotation);
        roomElement.setDimensionWidth(newWidth,roomView.getK());
        roomElement.setDimensionLength(newLenght,roomView.getK());
    }

    @Override
    public void undoCommand() {
        roomElement.setRotation(oldRotation);
        roomElement.setDimensionWidth(oldWidth,roomView.getK());
        roomElement.setDimensionLength(oldLenght,roomView.getK());
    }
}
