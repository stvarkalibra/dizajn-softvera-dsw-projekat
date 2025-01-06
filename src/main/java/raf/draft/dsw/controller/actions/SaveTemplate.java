package raf.draft.dsw.controller.actions;

import raf.draft.dsw.gui.swing.MainFrame;
import raf.draft.dsw.model.structures.Project;
import raf.draft.dsw.model.structures.Room;
import raf.draft.dsw.serializer.Serializer;
import raf.draft.dsw.serializer.TemplateSerializer;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.io.File;

public class SaveTemplate extends AbstractRoomAction{
    private final TemplateSerializer serializer;
    public SaveTemplate(TemplateSerializer serializer) {
        this.serializer = serializer;
        putValue(SMALL_ICON, loadIcon("/images/save.png"));
        putValue(NAME, "Save Template");
        putValue(SHORT_DESCRIPTION, "Save Project");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        save();
    }
    public void save(){
        int index= MainFrame.getInstance().getjTabbedPaneImplementation().getjTabbedPane().getSelectedIndex();
        Room room=MainFrame.getInstance().getjTabbedPaneImplementation().getRoomList().get(index);
        if (room == null) {
            System.out.println("No project to save.");
            return;
        }
        serializer.saveRoom(room);
    }
}

