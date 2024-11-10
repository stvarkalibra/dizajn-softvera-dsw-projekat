package raf.draft.dsw.jtabbedpane.view;

import raf.draft.dsw.controller.observer.ISubscriber;

import javax.swing.*;
import java.awt.*;

public class JTabbedPaneView extends JTabbedPane   {
    private JTabbedPane jTabbedPane;
    public JTabbedPaneView(){
        initialize();
    }
    public void initialize(){
        Toolkit kit=Toolkit.getDefaultToolkit();
        Dimension screenSize=kit.getScreenSize();
        int screenHeigt=screenSize.height;
        jTabbedPane=new JTabbedPane();
    }

    public JTabbedPane getjTabbedPane() {
        return jTabbedPane;
    }

    public void setjTabbedPane(JTabbedPane jTabbedPane) {
        this.jTabbedPane = jTabbedPane;
    }
}
