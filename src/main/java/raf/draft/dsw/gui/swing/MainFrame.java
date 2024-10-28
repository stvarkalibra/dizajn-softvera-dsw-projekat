package raf.draft.dsw.gui.swing;

import com.sun.tools.javac.Main;
import raf.draft.dsw.controller.observer.ISubscriber;
import raf.draft.dsw.core.ActionManager;
import raf.draft.dsw.model.message.Message;
import raf.draft.dsw.model.message.MessageType;

import javax.swing.*;
import java.awt.*;
import java.net.URL;
import java.time.LocalDateTime;

public class MainFrame extends JFrame implements ISubscriber {

    private ActionManager actionManager = new ActionManager();

    //buduca polja za sve komponente view-a na glavnom prozoru
    private static MainFrame mainFrameInstance;

    private MainFrame(){
        initialize();
    }

    public static MainFrame getInstance(){
        if(mainFrameInstance==null){
            mainFrameInstance=new MainFrame();
        }
        return mainFrameInstance;
    }

    private void initialize(){
        Toolkit kit = Toolkit.getDefaultToolkit();
        Dimension screenSize = kit.getScreenSize();
        int screenHeight = screenSize.height;
        int screenWidth = screenSize.width;
        setSize(screenWidth / 2, screenHeight / 2);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("DraftRoom");

        MyMenuBar menu = new MyMenuBar();
        setJMenuBar(menu);

        MyToolBar toolBar = new MyToolBar(actionManager);
        add(toolBar, BorderLayout.NORTH);
    }


    @Override
    public void update(Object o) {
        
        JOptionPane.showMessageDialog(null, o, "About Us",      JOptionPane.INFORMATION_MESSAGE);

    }
}
