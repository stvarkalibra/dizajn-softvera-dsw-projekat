package raf.draft.dsw.controller.actions;

import raf.draft.dsw.serializer.Gson;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.io.File;

public class OpenProjectAction extends AbstractRoomAction{
    private final Gson serializer;
    public OpenProjectAction(Gson serializer){
        this.serializer = serializer;
        putValue(SMALL_ICON, loadIcon("/images/addNode.png"));
        putValue(NAME, "AddNode");
        putValue(SHORT_DESCRIPTION, "Add New Node");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        open();
    }
    public void open() {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Open Project");

        if (fileChooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
            File selectedFile = fileChooser.getSelectedFile();
            serializer.loadProject(selectedFile);
            System.out.println("Project loaded successfully.");
        } else {
            System.out.println("Open operation canceled.");
        }
    }
}

