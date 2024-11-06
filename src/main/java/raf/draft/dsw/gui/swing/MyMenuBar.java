package raf.draft.dsw.gui.swing;

import raf.draft.dsw.controller.actions.ChangeInfoAction;
import raf.draft.dsw.controller.actions.ExitAction;
import raf.draft.dsw.controller.actions.NewNodeAction;
import raf.draft.dsw.controller.actions.RemoveNodeAction;

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
        fileMenu.add(exitAction);
        fileMenu.add(removeNodeAction);
        fileMenu.add(newNodeAction);
        fileMenu.add(changeInfoAction);
        add(fileMenu);
    }
}
