package raf.draft.dsw.commandPattern;

import raf.draft.dsw.jtabbedpane.view.RoomView;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class CommandManager {
    private List<AbstractCommand> commands=new ArrayList<>();
    private int currentCommand=0;
    private RoomView roomView;

    public CommandManager(RoomView roomView) {
        this.roomView = roomView;
    }

    public void addCommand(AbstractCommand command){
        while(currentCommand < commands.size())
            commands.remove(currentCommand);
        commands.add(command);
        currentCommand++;
    }
    public void doCommand(){
        if(currentCommand < commands.size()){
            commands.get(currentCommand++).doCommand();
        }
    }
    public void undoCommand(){
        if(currentCommand > 0){
            commands.get(--currentCommand).undoCommand();
        }
    }
}
