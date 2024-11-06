package raf.draft.dsw.core;

import raf.draft.dsw.controller.messagegenerator.ConsoleLogger;
import raf.draft.dsw.controller.messagegenerator.FileLogger;
import raf.draft.dsw.controller.messagegenerator.MessageGenerator;
import raf.draft.dsw.gui.swing.MainFrame;
import raf.draft.dsw.model.message.MessageType;
import raf.draft.dsw.model.repository.DraftExplorerImplementation;

public class ApplicationFramework {
    //buduca polja za model celog projekta
    private static ApplicationFramework appCore;
    protected DraftExplorerImplementation draftExplorerImplementation;

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
