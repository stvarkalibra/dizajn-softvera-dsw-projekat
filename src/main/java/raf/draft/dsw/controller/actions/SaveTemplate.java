package raf.draft.dsw.controller.actions;

import raf.draft.dsw.serializer.Serializer;

import java.awt.event.ActionEvent;

public class SaveTemplate extends AbstractRoomAction{
    public SaveTemplate() {
        putValue(SMALL_ICON, loadIcon("/images/save.png"));
        putValue(NAME, "Save Template");
        putValue(SHORT_DESCRIPTION, "Save Project");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        super.actionPerformed(e);
    }
}
