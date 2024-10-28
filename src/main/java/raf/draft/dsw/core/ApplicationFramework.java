package raf.draft.dsw.core;

import raf.draft.dsw.controller.messagegenerator.ConsoleLogger;
import raf.draft.dsw.controller.messagegenerator.FileLogger;
import raf.draft.dsw.controller.messagegenerator.MessageGenerator;
import raf.draft.dsw.gui.swing.MainFrame;
import raf.draft.dsw.model.message.Message;
import raf.draft.dsw.model.message.MessageType;

import java.awt.desktop.AppForegroundListener;
import java.lang.reflect.Type;

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

        MessageGenerator messageGenerator = new MessageGenerator();
        FileLogger fileLogger = new FileLogger("C:\\Users\\iivkovic9224si\\IdeaProjects\\dsw-projekat-tim_mihajlomarinkovic_ilijaivkovic\\src\\main\\resources\\files\\log.txt");
        ConsoleLogger consoleLogger = new ConsoleLogger();

        messageGenerator.addSubscriber(fileLogger);
        messageGenerator.addSubscriber(consoleLogger);
        messageGenerator.addSubscriber(mainFrame);

        String content = "NODE_CANNOT_BE_DELETED";
        MessageType type = MessageType.ERROR;

        messageGenerator.createMessage(content, type);
    }
}
