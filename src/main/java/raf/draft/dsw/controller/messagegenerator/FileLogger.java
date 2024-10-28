package raf.draft.dsw.controller.messagegenerator;

import raf.draft.dsw.model.message.Message;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class FileLogger implements ILogger {
    String filePath;

    public FileLogger(String filePath) {
        this.filePath = filePath;
    }

    @Override
    public void update(Object o) {
        if (o instanceof Message){
            try {
                System.out.println(filePath);
                File file = new File(filePath);
                FileWriter fw = new FileWriter(file, true);
                System.out.println(o.toString());
                fw.write(o.toString());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
