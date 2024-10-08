package raf.draft.dsw.core;

import raf.draft.dsw.gui.swing.MainFrame;

import java.awt.desktop.AppForegroundListener;

public class ApplicationFramework {
    //buduca polja za model celog projekta
    private static ApplicationFramework appCore;

    private ApplicationFramework(){
        initialize();
    }

    public static ApplicationFramework getInstance(){
        if(appCore==null){
            appCore=new ApplicationFramework();
        }
        return appCore;
    }

    public void initialize(){
        MainFrame mainFrame=MainFrame.getInstance();
        mainFrame.setVisible(true);
    }
}
