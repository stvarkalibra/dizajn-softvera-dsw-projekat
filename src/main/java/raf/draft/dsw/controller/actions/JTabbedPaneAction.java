package raf.draft.dsw.controller.actions;

import raf.draft.dsw.gui.swing.MainFrame;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;

public class JTabbedPaneAction extends AbstractRoomAction{

    @Override
    public void actionPerformed(ActionEvent e) {
        MainFrame.getInstance().getjTree().addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent evt) {
                if (evt.getClickCount() == 2) {
                    System.out.println("uso");
                }
            }
        });
    }
}