package raf.draft.dsw.controller.messagegenerator;

public class LoggerFactory {
    public ILogger createLogger(String type, String filePath){
        if (type.equals("consoleLogger")){
            return new ConsoleLogger();
        }
        else if (type.equals("fileLogger")){
            return new FileLogger(filePath);
        }
        else return null;
    }
}
