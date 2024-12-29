package raf.draft.dsw.controller.actions;

import raf.draft.dsw.gui.swing.MainFrame;
import raf.draft.dsw.model.structures.Project;
import raf.draft.dsw.model.structures.Room;
import raf.draft.dsw.serializer.Gson;
import raf.draft.dsw.serializer.Serializer;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.io.File;
import java.util.Random;

public class ProjectSaveAction extends AbstractRoomAction{
    private final Serializer serializer;

    public ProjectSaveAction(Serializer serializer) {
        this.serializer = serializer;
        putValue(SMALL_ICON, loadIcon("/images/save.png"));
        putValue(NAME, "Save Project");
        putValue(SHORT_DESCRIPTION, "Save Project");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        save();
    }

    public void save() {
        int index = MainFrame.getInstance().getjTabbedPaneImplementation().getjTabbedPane().getSelectedIndex();
        Room room = MainFrame.getInstance().getjTabbedPaneImplementation().getRoomList().get(index);
        Project project;
        if(room.getParent() instanceof Project){
            project=(Project) room.getParent();
        }else{
            project=(Project) room.getParent().getParent();
        }
        if (project == null) {
            System.out.println("No project to save.");
            return;
        }

        if (project.getPathToFolder().isEmpty()) {
            JFileChooser fileChooser = new JFileChooser();
            fileChooser.setDialogTitle("Save Project");

            if (fileChooser.showSaveDialog(null) == JFileChooser.APPROVE_OPTION) {
                File selectedFile = fileChooser.getSelectedFile();
                project.setPathToFolder(selectedFile.getAbsolutePath());
                System.out.println("Project path set to: " + selectedFile.getAbsolutePath());
            } else {
                System.out.println("Save operation canceled.");
                return;
            }
        }
        if (!project.isChanged()) {
            System.out.println("No changes to save.");
            return;
        }
        serializer.saveProject(project);
    }
}
