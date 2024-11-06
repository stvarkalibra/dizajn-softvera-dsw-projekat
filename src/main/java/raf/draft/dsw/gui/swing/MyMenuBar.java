package raf.draft.dsw.gui.swing;

import raf.draft.dsw.controller.actions.*;

import javax.swing.*;
import java.awt.event.KeyEvent;

public class MyMenuBar extends JMenuBar {
    public MyMenuBar(){
        JMenu fileMenu = new JMenu("File");
        fileMenu.setMnemonic(KeyEvent.VK_F);
        ExitAction exitAction = new ExitAction();
        NewNodeAction newNodeAction=new NewNodeAction();
        RemoveNodeAction removeNodeAction=new RemoveNodeAction();
        ChangeInfoAction changeInfoAction=new ChangeInfoAction();
        AboutUsAction aboutUsAction=new AboutUsAction();
        fileMenu.add(exitAction);
        fileMenu.add(newNodeAction);
        fileMenu.add(changeInfoAction);
        fileMenu.add(removeNodeAction);
        fileMenu.add(aboutUsAction);
        add(fileMenu);
    }
}
