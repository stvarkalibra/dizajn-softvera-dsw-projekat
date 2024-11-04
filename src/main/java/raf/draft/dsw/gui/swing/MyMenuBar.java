package raf.draft.dsw.gui.swing;

import raf.draft.dsw.controller.actions.ExitAction;
import raf.draft.dsw.controller.actions.NewNodeAction;

import javax.swing.*;
import java.awt.event.KeyEvent;

public class MyMenuBar extends JMenuBar {
    public MyMenuBar(){
        JMenu fileMenu = new JMenu("File");
        fileMenu.setMnemonic(KeyEvent.VK_F);
        ExitAction ea = new ExitAction();
        NewNodeAction newNodeAction=new NewNodeAction();
        fileMenu.add(ea);
        fileMenu.add(newNodeAction);
        add(fileMenu);
    }
}
