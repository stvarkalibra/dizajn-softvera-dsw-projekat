package raf.draft.dsw.serializer;

import com.fasterxml.jackson.databind.ObjectMapper;
import raf.draft.dsw.model.structures.Project;

import java.io.File;
import java.io.IOException;

public class JacksonSerializer implements Serializer{
    private final ObjectMapper objectMapper = new ObjectMapper();
    @Override
    public Project loadProject(File file) {
        try {
            Project project = objectMapper.readValue(file, Project.class);
            project.setChanged(false);
            return project;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public void saveProject(Project project) {
        File file = new File(project.getPathToFolder());
        try {
            objectMapper.writeValue(file, project);
            project.setChanged(false);
            System.out.println("Project saved successfully.");
        } catch (IOException e) {
            System.err.println("Error saving project to file: " + e.getMessage());
        }
    }

}
