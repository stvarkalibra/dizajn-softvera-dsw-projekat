package raf.draft.dsw.serializer;

import com.fasterxml.jackson.databind.ObjectMapper;
import raf.draft.dsw.model.structures.Project;
import raf.draft.dsw.model.structures.Room;

import java.io.File;
import java.io.IOException;

public class TemplateSerializer{
    private final ObjectMapper objectMapper = new ObjectMapper();
    public Room loadRoom(File file) {
        try {
            return objectMapper.readValue(file, Room.class);
        } catch (IOException e) {
            System.err.println("Error loading room from file: " + e.getMessage());
            return null;
        }
    }
    public void saveRoom(Room room) {
        File file = new File("src/main/resources/templates", room.getNodeName()+"Template.json");
        try {
            objectMapper.writeValue(file, room);
            System.out.println("Room saved successfully.");
        } catch (IOException e) {
            System.err.println("Error saving room to file: " + e.getMessage());
        }
    }
}
