package raf.draft.dsw.controller;

import raf.draft.dsw.gui.swing.AboutUsFrame;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

public class AboutUsAction extends AbstractRoomAction {
    private AboutUsFrame aboutUsFrame = new AboutUsFrame();
    public AboutUsAction(){
        //bitno
        putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_F5, ActionEvent.ALT_MASK));
        putValue(SMALL_ICON, loadIcon("/images/aboutUs.png"));
        putValue(NAME, "About Us");
        putValue(SHORT_DESCRIPTION, "About Us");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
       aboutUsFrame.setVisible(true);
    }
}
