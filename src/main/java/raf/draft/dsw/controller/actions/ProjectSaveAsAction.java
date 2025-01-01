package raf.draft.dsw.controller.actions;

import raf.draft.dsw.gui.swing.MainFrame;
import raf.draft.dsw.model.structures.Project;
import raf.draft.dsw.model.structures.Room;
import raf.draft.dsw.serializer.Gson;
import raf.draft.dsw.serializer.Serializer;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.io.File;

public class ProjectSaveAsAction extends AbstractRoomAction{
    private final Serializer serializer;

    public ProjectSaveAsAction(Serializer serializer) {
        this.serializer = serializer;
        putValue(SMALL_ICON, loadIcon("/images/save_as.png"));
        putValue(NAME, "Save Project As");
        putValue(SHORT_DESCRIPTION, "Save Project As");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        saveAs();
    }

    public void saveAs() {
        Project project=(Project) MainFrame.getMainFrameInstance().getDraftTree().getSelectedNode().getDraftNode();
        if (project == null) {
            System.out.println("No project to save.");
            return;
        }

        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Save Project As");

        if (fileChooser.showSaveDialog(null) == JFileChooser.APPROVE_OPTION) {
            File selectedFile = fileChooser.getSelectedFile();
            project.setPathToFolder(selectedFile.getAbsolutePath());
            serializer.saveProject(project);
        } else {
            System.out.println("Save As operation canceled.");
        }
    }
}
