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
        if (o instanceof Message) {
            try {
                File file = new File(filePath);

                // Create parent directories if they don’t exist
                file.getParentFile().mkdirs();

                // Use try-with-resources to handle closing the FileWriter
                try (FileWriter fw = new FileWriter(file, true)) {
                    fw.write(o.toString());
                }

                System.out.println("File written successfully.");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
