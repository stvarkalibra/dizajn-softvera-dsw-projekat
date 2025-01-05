package raf.draft.dsw.controller.actions;

import raf.draft.dsw.serializer.Serializer;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class OpenTemplate extends AbstractRoomAction{
    public OpenTemplate() {
        putValue(SMALL_ICON, loadIcon("/images/save.png"));
        putValue(NAME, "Open Template");
        putValue(SHORT_DESCRIPTION, "Save Project");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        openTemplate();
    }
    public void openTemplate(){

    }
}
